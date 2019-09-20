<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";
use Ubiqube\EtsiMano\VnfPkgSol003;
use Ubiqube\EtsiMano\ManoException;

use Symfony\Component\Yaml\Yaml;

function list_args()
{
	create_var_def('nfvoDevice', 'Device');
        create_var_def('vnfPkgId', 'String');
}
check_mandatory_param('vnfPkgId');

//$vnfPkgManagement = new VnfPkgSol003('http://localhost:8380/ubi-etsi-mano-0.0.1-SNAPSHOT/');
$vnfPkgManagement = new VnfPkgSol003('http://localhost:8380/ubi-etsi-mano/');
try {
	$vnfPkg = $vnfPkgManagement->vnfPackagesVnfPkgIdGet($context['vnfPkgId']);
} catch (ManoException $e) {
	task_error($e->getMessage());
}

// Check VNFPkg operational state
if ($vnfPkg['operationalState'] == "DISABLED") {
	task_exit(FAILED, "VNF Package with ID: " . $context['vnfPkgId'] . " cannot instantiate, its operational state is DISABLED.");
}
$heatJson = "";
if(array_key_exists('heat', $vnfPkg['userDefinedData'])) {
	$heatJson = $vnfPkg['userDefinedData']['heat'];
	foreach($heatJson['resources'] as &$resource) {
		if($resource['type'] == 'OS::Nova::Server') {
			$resource['properties']['name'] = $context['SERVICEINSTANCEREFERENCE'];
		}
	}
	$context['image'] = $vnfPkg['userDefinedData']['heat']['parameters']['simulator_image_id']['default'];
	$context['flavor'] = $vnfPkg['userDefinedData']['heat']['parameters']['flavor']['default'];
	$context['old_flavor'] = $context['flavor'];
	$content = Yaml::dump($heatJson);
} else {
	$content = $vnfPkgManagement->vnfPackagesVnfPkgIdPackageContentGet($context['vnfPkgId']);
}
// Patch first resource entry.
//$firstEntry = key($heatJson['resources']);
//$heatJson['resources'][$context['SERVICEINSTANCEREFERENCE']] = $heatJson['resources'][$firstEntry];



$path = '/opt/ses/share/htdocs/tech_report/vnf_packages/heat/' . $context['vnfPkgId'];
@mkdir($path);
file_put_contents($path . '/vnfd.yaml', $content);
$context['heatJson'] = serialize($heatJson);
$context['deviceid'] = $vnfPkg['userDefinedData']['vimId'];
$context['stackname'] = $context['SERVICEINSTANCEREFERENCE'];
$context['manufacturerId'] = $vnfPkg['userDefinedData']['manufacturerId'];
$context['modelId'] = $vnfPkg['userDefinedData']['modelId'];
$context['device_login'] = $vnfPkg['userDefinedData']['device_login'];
$context['device_password'] = $vnfPkg['userDefinedData']['device_password'];

//$context['simulator_image_id'] = $vnfPkg['userDefinedData']['heat']['parameters']['simulator_image_id']['default'];

// TODO get this IP on a NFVO
$context['template_url'] = 'http://10.31.1.246/tech_report/vnf_packages/heat/' . $context['vnfPkgId'].'/vnfd.yaml';

//print_r($vnfPkg);
task_exit(ENDED, "VNFD fetched successfully from NFVO - VNF Catalogs.");

<?php

require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';


use Ubiqube\EtsiMano\NsdSol005;
use Symfony\Component\Yaml\Yaml;

function list_args()
{
  create_var_def('nsPkgId', 'String');
  create_var_def('vnfvo_device', 'String');
}

check_mandatory_param('nsPkgId');

$nsPkgId = $context['nsPkgId'];
$url = get_url_from_device($context['vnfvo_device']);
$nsPkgManagement = new NsdSol005($url);
$nsPkgInfo = $nsPkgManagement->nsDescriptorsNsdInfoIdGet($nsPkgId);

$vnfPkgIds = $nsPkgInfo['vnfPkgIds'];

$i=0;
foreach ($nsPkgInfo['vnfPkgIds'] as $vnfPkgId) {
	$context['vnfPkgs'][$i++]['vnfPkgId']=$vnfPkgId;
}


task_exit(ENDED, "VNF Packages IDs linked to the NSPackage with ID: $nsPkgId are stored in the context.");

?>

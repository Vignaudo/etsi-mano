<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";


use Ubiqube\EtsiMano\NsdSol005;
use Ubiqube\EtsiMano\ManoException;

function list_args()
{
	create_var_def('nsdPkgId', 'string');
}

$nsdApi = new NsdSol005('http://localhost:8380/ubi-etsi-mano/');
try {
	$nsdApi->nsDescriptorsNsdInfoIdDelete($context['nsdPkgId']);
} catch (ManoException $e) {
	task_error($e->getMessage());
}

unset($context['nsdPkgId']);
task_success("NSD Suppressed.");
?>

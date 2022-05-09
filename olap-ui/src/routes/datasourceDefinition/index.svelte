<script>
	import axios from 'axios';
    import { onMount } from 'svelte';
    import Card from "sveltestrap/src/Card.svelte";
    import CardBody from "sveltestrap/src/CardBody.svelte";
    import CardHeader from "sveltestrap/src/CardHeader.svelte";
    import CardFooter from "sveltestrap/src/CardFooter.svelte";
    import Form from "sveltestrap/src/Form.svelte";
    import Button from "sveltestrap/src/Button.svelte";
  	import FormGroup from "sveltestrap/src/FormGroup.svelte";
  	import Label from "sveltestrap/src/Label.svelte";
  	import Input from "sveltestrap/src/Input.svelte";

    let datasources=[]

    let newDatasource = {
        "label": "",
        "kind": "",
        "driver": "",
        "login": "",
        "password": "",
        "url": "",
        "database": "",
        "lastUpdate": ""
    };

onMount(async () => {
    getDatasources();
});


let getDatasources = () => {
    axios
        .get('http://localhost:8080/datasources')
        .then(function (response) {
            datasources = response.data
        })
        .catch(function (error) {
            console.log(error);
        });
}
	
</script>
<div style="margin:auto; width:fit-content;" >
    <Card class="shadow-lg border-0 rounded-lg mt-5" style="width:fit-content; margin:auto;" >
        <CardHeader>
        <h3 class="text-center font-weight-light my-4">Datasources</h3>
        </CardHeader>
        <CardBody >
            <table class="table table-bordered">
            <tr><th>ID</th><th>Label</th><th>Type</th><th>Driver</th><th>Login</th><th>Password</th><th>URL</th><th>Database</th><th>Last Update</th></tr>
            {#each datasources as ds}
                <tr><td>{ds.id}</td><td>{ds.label}</td><td>{ds.kind}</td><td>{ds.driver}</td><td>{ds.login}</td><td>{ds.password}</td><td>{ds.url}</td><td>{ds.database}</td><td>{ds.lastUpdate}</td></tr>
            {/each}
            </table> 
        </CardBody>
    </Card>
</div>
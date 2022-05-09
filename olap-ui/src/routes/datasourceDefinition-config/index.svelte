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
let addDatasource = () => {
    axios
        .post('http://localhost:8080/datasources', newDatasource)
        .then(function (response) {
            newDatasource.label="";
            newDatasource.kind="";
            newDatasource.driver="";
            newDatasource.login="";
            newDatasource.password="";
            newDatasource.url="";
            newDatasource.database="";
            newDatasource.lastUpdate="";
            getDatasources();
        })
        .catch(function (error) {
            console.log(error);
        });
}
	
</script>
<div style="margin:auto; width:fit-content;" >
    <Card class="shadow-lg border-0 rounded-lg mt-5" style="width:fit-content; margin:auto;" >
        <CardHeader>
        <h3 class="text-center font-weight-light my-4">Add Datasource</h3>
        </CardHeader>
        <CardBody >
        <Form >
            <FormGroup>
            <Label for="titleDash" class="small mb-1">Datasource Label: </Label>
            <Input
            class="py-4"
            type="text"
            name="firstName"
            id="inputFirstName"
            placeholder="Enter datasource label" 
            bind:value={newDatasource.label}/><br>
            <Label for="titleDash" class="small mb-1">Datasource Type: </Label>
            <Input
            class="py-4"
            type="text"
            name="firstName"
            id="inputFirstName"
            placeholder="Enter datasource type" 
            bind:value={newDatasource.kind}/><br>
            <Label for="titleDash" class="small mb-1">Datasource Driver: </Label>
            <Input
            class="py-4"
            type="text"
            name="firstName"
            id="inputFirstName"
            placeholder="Enter datasource driver" 
            bind:value={newDatasource.driver}/><br>
            <Label for="titleDash" class="small mb-1">Datasource Login: </Label>
            <Input
            class="py-4"
            type="text"
            name="firstName"
            id="inputFirstName"
            placeholder="Enter datasource login" 
            bind:value={newDatasource.login}/><br>
            <Label for="titleDash" class="small mb-1">Datasource Password: </Label>
            <Input
            class="py-4"
            type="password"
            name="firstName"
            id="inputFirstName"
            placeholder="Enter datasource password"
            bind:value={newDatasource.password}/><br>   
            <Label for="titleDash" class="small mb-1">Datasource URL: </Label>
            <Input
            class="py-4"
            type="text"
            name="firstName"
            id="inputFirstName"
            placeholder="Enter datasource url" 
            bind:value={newDatasource.url}/><br>
            <Label for="titleDash" class="small mb-1">Database: </Label>
            <Input
            class="py-4"
            type="text"
            name="firstName"
            id="inputFirstName"
            placeholder="Enter database" 
            bind:value={newDatasource.database}/><br>         
            <button class="button" style="margin-left:45%;"  type="submit" on:click|preventDefault={addDatasource} >Add</button>   
        </FormGroup>
                
                
        </Form>
        
            <br><br>
            <table class="table table-bordered">
            <tr><th>ID</th><th>Label</th><th>Type</th><th>Driver</th><th>Login</th><th>Password</th><th>URL</th><th>Database</th><th>Last Update</th></tr>
            {#each datasources as ds}
                <tr><td>{ds.id}</td><td>{ds.label}</td><td>{ds.kind}</td><td>{ds.driver}</td><td>{ds.login}</td><td>{ds.password}</td><td>{ds.url}</td><td>{ds.database}</td><td>{ds.lastUpdate}</td></tr>
            {/each}
            </table> 
        </CardBody>
    </Card>
</div>
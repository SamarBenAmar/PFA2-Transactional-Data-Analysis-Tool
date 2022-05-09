<script>
    import axios from 'axios';
	import { page } from '$app/stores';
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


    let datasource = {}

    let newDebeziumConnection = {
        "name": "",
        "config": {
            "connector.class": "",
            "tasks.mask": "",
            "database.hostname": "",
            "database.port": "",
            "database.user": "",
            "database.password": "",
            "database.dbname" : "",
            "database.server.name": "",
        	"schema.include.list": ""
        }
    }

    let getDataSource = (id) => {
		axios
			.get('http://localhost:8080/datasources/' + id)
			.then(function (response) {
				console.log(response);
				datasource = response.data;
			})
			.catch(function (error) {
				console.log(error);
			});
	}

    let addDBConnection = () => {
		axios
			.post('http://localhost:8080/dashboards/'+datasource.id, newDebeziumConnection)
			.then(function (response) {
				getDataSource(datasource.id);
			})
			.catch(function (error) {
				console.log(error);
			});
	};

    onMount(async () => {
		let id = $page.params.id;
		getDataSource(id);
	});
</script>
<div style="margin:auto;" >
	<Card class="shadow-lg border-0 rounded-lg mt-5" style="width:1000px; margin:auto;" >
		<CardHeader>
		  <h3 class="text-center font-weight-light my-4">ID Datasource : {datasource.id}</h3>
		  <h3 class="text-center font-weight-light my-4">Datasource Type : {datasource.kind}</h3>
		</CardHeader>
	</Card>
	<Card class="shadow-lg border-0 rounded-lg mt-5" style="width:1000px; margin:auto;">
		<CardHeader>
			<h3 class="text-center font-weight-light my-4">Connect Debezium To Datasource {datasource.id}</h3>
		</CardHeader>
		<CardBody>
			<Form >
				<FormGroup>
				<Label for="typeGraph" class="small mb-1">Connection name: </Label>
				<Input
				  class="py-4"
				  type="text"
				  name="firstName"
				  id="inputFirstName"
				  placeholder="Enter connection name" 
				  bind:value={newDebeziumConnection.name}/><br>
				  <Label for="typeGraph" class="small mb-1">Connection configuration: </Label>
				  <Input
					class="py-4"
					type="text"
					name="firstName"
					id="inputFirstName"
					placeholder="Enter connection class" 
					bind:value={newDebeziumConnection.config['connector.class']}/><br>
					<Input
					class="py-4"
					type="text"
					name="firstName"
					id="inputFirstName"
					placeholder="Enter database host name" 
					bind:value={newDebeziumConnection.config['database.hostname']}/><br>
					<Input
					class="py-4"
					type="text"
					name="firstName"
					id="inputFirstName"
					placeholder="Enter database port" 
					bind:value={newDebeziumConnection.config['database.port']}/><br>
					<Input
					class="py-4"
					type="text"
					name="firstName"
					id="inputFirstName"
					placeholder="Enter database user" 
					bind:value={newDebeziumConnection.config['database.user']}/><br>
					<Input
					class="py-4"
					type="text"
					name="firstName"
					id="inputFirstName"
					placeholder="Enter database password" 
					bind:value={newDebeziumConnection.config['database.password']}/><br>
					<Input
					class="py-4"
					type="text"
					name="firstName"
					id="inputFirstName"
					placeholder="Enter database name" 
					bind:value={newDebeziumConnection.config['database.dbname']}/><br>
					<Input
					class="py-4"
					type="text"
					name="firstName"
					id="inputFirstName"
					placeholder="Enter database server name" 
					bind:value={newDebeziumConnection.config['database.server.name']}/><br>
					<Input
					class="py-4"
					type="text"
					name="firstName"
					id="inputFirstName"
					placeholder="Enter schema include list" 
					bind:value={newDebeziumConnection.config['schema.include.list']}/><br>
					<button class="button" style="margin-left:45%;"  type="submit" on:click|preventDefault={addDBConnection} >Add</button>   

				</FormGroup>
			</Form>
		</CardBody>
	</Card>
</div>
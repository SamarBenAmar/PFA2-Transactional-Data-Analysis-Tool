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


	let dashboard = {};
	let newGraph = {
		"type": "",
		"title": "",
		"xaxisTitle": "",
		"yaxisTitle": "",
		"sqlQuery": "",
		"idDatasourceDefinition": null
	};

	let getDashboard = (idDashboard) => {
		axios
			.get('http://localhost:8080/dashboards/' + idDashboard)
			.then(function (response) {
				console.log(response);
				dashboard = response.data;
			})
			.catch(function (error) {
				console.log(error);
			});
	}
    let addGraph = () => {
		axios
			.post('http://localhost:8080/dashboards/'+dashboard.idDashboard, newGraph)
			.then(function (response) {
				getDashboard(dashboard.idDashboard);
			})
			.catch(function (error) {
				console.log(error);
			});
	};

	onMount(async () => {
		let idDashboard = $page.params.idDashboard;
		getDashboard(idDashboard);
	});
</script>
<div style="margin:auto;" >
	<Card class="shadow-lg border-0 rounded-lg mt-5" style="width:1000px; margin:auto;">
		<CardHeader>
		  <h3 class="text-center font-weight-light my-4">ID Dashboard : {dashboard.idDashboard}</h3>
		  <h3 class="text-center font-weight-light my-4">Dashboard Title : {dashboard.title}</h3>
		</CardHeader>
<!-- id : {dashboard.idDashboard}
title : {dashboard.title} -->
		<CardBody >
			{#if dashboard.graphs}
				<table class="table table-bordered">
					<tr><th>ID Graph</th><th>Graph Title</th><th>X Axis Title</th><th>Y Axis Title</th><th>sql</th><th>Datasource ID</th></tr>
					{#each dashboard.graphs as g}
						<tr><td>{g.idGraph}</td><td>{g.title}</td><td>{g.xaxisTitle}</td><td>{g.yaxisTitle}</td><td>{g.sqlQuery}</td><td>{g.datasource.id}</td></tr>
					{/each}
				</table>
			{/if}
		</CardBody>
	</Card>
	<Card class="shadow-lg border-0 rounded-lg mt-5" style="width:1000px; margin:auto;">
		<CardHeader>
			<h3 class="text-center font-weight-light my-4">Add Graph To Dashboard {dashboard.idDashboard}</h3>
		</CardHeader>
		<CardBody>
			<Form >
				<FormGroup>
				<Label for="typeGraph" class="small mb-1">Graph Type: </Label> <br>
				<select class="py-3" id="firstName" style="border-radius:5px; border-color:grey;" bind:value={newGraph.type} >
					<option value="" selected>--Choose the graph type--</option>
					<option value="bar">Bar</option>
					<option value="pie">Pie</option>
					<option value="line">Line</option>
					<option value="bubble">Bubble</option>
					<option value="radar">Radar</option>
				</select>
<!-- 				<Input
				  class="py-4"
				  type="text"
				  name="firstName"
				  id="inputFirstName"
				  placeholder="Enter graph type" 
				  bind:value={newGraph.type}/> --><br><br>
				<Label for="titleGraph" class="small mb-1">Graph Title: </Label>
				<Input
					class="py-4"
					type="text"
					name="firstName"
					id="inputFirstName"
					placeholder="Enter graph title" 
					bind:value={newGraph.title}/><br>
				<Label for="titleGraph" class="small mb-1">X Axis Title: </Label>
				<Input
					class="py-4"
					type="text"
					name="firstName"
					id="inputFirstName"
					placeholder="Enter x axis title" 
					bind:value={newGraph.xaxisTitle}/><br>
					<Label for="titleGraph" class="small mb-1">Y Axis Title: </Label>
					<Input
						class="py-4"
						type="text"
						name="firstName"
						id="inputFirstName"
						placeholder="Enter y axis title" 
						bind:value={newGraph.yaxisTitle}/><br>	
						<Label for="titleGraph" class="small mb-1">Datasource ID: </Label>
						<Input
							class="py-4"
							type="text"
							name="firstName"
							id="inputFirstName"
							placeholder="Enter datasource id" 
							bind:value={newGraph.idDatasourceDefinition}/><br>
							<Label for="titleGraph" class="small mb-1">SQL Query: </Label>
							<Input
								class="py-4"
								type="text"
								name="firstName"
								id="inputFirstName"
								placeholder="Enter a sql query" 
								bind:value={newGraph.sqlQuery}/><br>								
				  			<button class="button" style="margin-left:45%;"  type="submit" on:click|preventDefault={addGraph} >Add</button>   
			  </FormGroup>
			  </Form>
		</CardBody>
		<CardFooter class="text-center small">
			<p>Real Time Data Analysis!</p>
		</CardFooter>
	</Card>

<br/>
<!-- Add graph to dashboard {dashboard.idDashboard}
<form>
	type : <input type="text" bind:value={newGraph.type} /><br/>
	title : <input type="text" bind:value={newGraph.title} /><br/>
	xaxisTitle : <input type="text" bind:value={newGraph.xaxisTitle} /><br/>
	yaxisTitle : <input type="text" bind:value={newGraph.yaxisTitle} /><br/>
	idDatasourceDefinition : <input type="text" bind:value={newGraph.idDatasourceDefinition} /><br/>
	sqlQuery : <input type="text" bind:value={newGraph.sqlQuery} /><br/>
	<button type="submit" on:click|preventDefault={addGraph}>Add graph</button>
</form> -->
</div>

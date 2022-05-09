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

	let dashboards=[]

	onMount(async () => {
		getDashboards();
	});


    let getDashboards = () => {
        axios
			.get('http://localhost:8080/dashboards')
			.then(function (response) {
                dashboards = response.data
			})
			.catch(function (error) {
				console.log(error);
			});
    }
	
</script>
<div style="margin:auto; width:fit-content;">
    <Card class="shadow-lg border-0 rounded-lg mt-5" style="width:1000px; margin:auto;" >
        <CardHeader>
        <h3 class="text-center font-weight-light my-4">Dashboards</h3>
        </CardHeader>
        <CardBody >
<table class="table table-bordered">
			<tr><th>ID</th><th>Title</th><th>View</th></tr>
			{#each dashboards as d}
				<tr><td>{d.idDashboard}</td><td>{d.title}</td><td><a href="dashboards/{d.idDashboard}"><img alt="" src="https://img.icons8.com/fluency-systems-regular/25/000000/view-file.png" /></a></td></tr>
			{/each}
</table> 
		</CardBody>
		</Card>
</div>
<!-- <table> 
    <tr><th>idDashboar</th><th>title</th><th>voir</th></tr>
    {#each dashboards as d}
        <tr><td>{d.idDashboard}</td><td>{d.title}</td><td><a href="dashboards/{d.idDashboard}">voir</a></td></tr>
    {/each}
</table> -->
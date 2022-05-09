<script>
	import axios from 'axios';
	import { page } from '$app/stores';
	import { onMount } from 'svelte';
	import 'charts.css';
	import Bar from 'svelte-chartjs/src/Bar.svelte';
	import Pie from 'svelte-chartjs/src/Pie.svelte';
	import Line from 'svelte-chartjs/src/Line.svelte';
	import Bubble from 'svelte-chartjs/src/Bubble.svelte';
	import Radar from 'svelte-chartjs/src/Radar.svelte';
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
	let options = { responsive: true};
	const COLORS = [
  '#4dc9f6',
  '#f67019',
  '#f53794',
  '#537bc4',
  '#acc236',
  '#166a8f',
  '#00a950',
  '#58595b',
  '#8549ba'
];
	let getDashboardData = (idDashboard) => {
		axios
			.get('http://localhost:8080/dashboards/' + idDashboard + '/data')
			.then(function (response) {
				dashboard = response.data;
			})
			.catch(function (error) {
				console.log(error);
			});
	};

	onMount(async () => {
		let idDashboard = $page.params.idDashboard;
		getDashboardData(idDashboard);
	});
</script>



<Card class="shadow-lg border-0 rounded-lg mt-5">
<CardHeader><h3>ID Dashboard: {dashboard.id}</h3>
<h3>Dashboard Title: {dashboard.title}</h3></CardHeader></Card><hr>
<h1>Using Charts.CSS Library</h1>
 {#if dashboard.graphs}
	{#each dashboard.graphs as g}
	{#if g.type == "bar"}
	<br><br><br>
	<div style="height: 300px">
	<Card class="shadow-lg border-0 rounded-lg mt-5">
		<CardHeader>
			<h1>{g.title}</h1>
		</CardHeader>
		<CardBody>
		<table class="charts-css {g.type} show-labels">
			<caption> {g.title}</caption>
			<thead><tr><th scope="col">{g.xaxisTitle} </th><th scope="col">{g.yaxisTitle}</th></tr></thead>
			<tbody>
				{#each g.data[g.xaxisTitle] as valX, i}
					<tr>
						<th scope="row"> {valX} </th>
						<td style="--size: calc( {g.data[g.yaxisTitle][i]}/max({g.data[g.yaxisTitle]}))">
							{g.data[g.yaxisTitle][i]}
						</td>
					</tr>
				{/each}
			</tbody> 
		</table></CardBody>
	</Card>
</div> <hr>
	{/if}
	{/each}
{/if}   

<h1>Using Charts.JS Library</h1>

{#if dashboard.graphs}
	{#each dashboard.graphs as g}
	{#if g.type == "bar"}
	<br><br><br>
	<div style="height: 300px">
	<Card class="shadow-lg border-0 rounded-lg mt-5">
		<CardHeader>
			<h1>{g.title}</h1>
		</CardHeader>
		<CardBody>
		<Bar 
			data={{
				labels: g.data[g.xaxisTitle],
				datasets: [
					{
						label: g.yaxisTitle,
						data: g.data[g.yaxisTitle],
						backgroundColor:COLORS
					}
				]
			}}
			width={300}
			height={300}
			options={{ maintainAspectRatio: false, responsive: true }}
		/></CardBody>
	</Card>
</div> <hr>
	{/if}
	{/each}
{/if}



{#if dashboard.graphs}
	{#each dashboard.graphs as g}
	{#if g.type == "pie"}
	<div style="height: 500px">
		<Card class="shadow-lg border-0 rounded-lg mt-5">
			<CardHeader>
				<h1>{g.title}</h1>
			</CardHeader>
			<CardBody>
		<Pie
			data={{
				labels: g.data[g.xaxisTitle],
				datasets: [
					{
						data: g.data[g.yaxisTitle],
						backgroundColor:COLORS
					}
				]
				
			}}
			width={500}
			height={300}
			options={{ maintainAspectRatio: false, responsive: true }}
		/></CardBody>
	</Card>
</div> <hr>
	{/if}
	{/each}
{/if}

{#if dashboard.graphs}
	{#each dashboard.graphs as g}
	{#if g.type == "line"}
	<br><br><br>
	<div style="height: 200px">
	<Card class="shadow-lg border-0 rounded-lg mt-5">
		<CardHeader>
			<h1>{g.title}</h1>
		</CardHeader>
		<CardBody>
		<Line
		
			data={{
				labels: g.data[g.xaxisTitle],
				datasets: [
					{
						label: g.yaxisTitle,
						data: g.data[g.yaxisTitle],
						backgroundColor:COLORS,
						borderColor: COLORS
					}
				]
			}}
			width={200}
			height={300}
			options={{ maintainAspectRatio: false, responsive: true }}
		/>		</CardBody>
	</Card>
</div> <hr>
	{/if}
	{/each}
{/if}

{#if dashboard.graphs}
	{#each dashboard.graphs as g}
	{#if g.type == "radar"}
	<br><br><br><br><br><br>
	<div style="height: 200px">
	<Card class="shadow-lg border-0 rounded-lg mt-5">
		<CardHeader>
			<h1>{g.title}</h1>
		</CardHeader>
		<CardBody>
		<Radar
		
			data={{
				labels: g.data[g.xaxisTitle],
				datasets: [
					{
						label: g.yaxisTitle,
						data: g.data[g.yaxisTitle],
						backgroundColor:COLORS,
						borderColor: COLORS
					}
				]
			}}
			width={200}
			height={300}
			options={{ maintainAspectRatio: false, responsive: true }}
		/></CardBody>
	</Card>
</div> <hr><hr>
	{/if}
	{/each}
{/if}

{#if dashboard.graphs}
	{#each dashboard.graphs as g}
	{#if g.type == "bubble"}
	<hr><br><br><br> <br> <br>
	<div style="height: 200px">
	<Card class="shadow-lg border-0 rounded-lg mt-5">
		<CardHeader>
			<h1>{g.title}</h1>
		</CardHeader>
		<CardBody>
		<Bubble
		
			data={{
				labels: g.data[g.xaxisTitle],
				datasets: [
					{
						label: g.yaxisTitle,
						data: g.data[g.yaxisTitle],
						backgroundColor:COLORS,
						borderColor: COLORS
					}
				]
			}}
			width={200}
			height={300}
			options={{ maintainAspectRatio: false, responsive: true }}
		/>		</CardBody>
	</Card>
</div> <hr>
	{/if}
	{/each}
{/if}


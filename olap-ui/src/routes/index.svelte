<!-- <a href="dashboards-config">dashboards config</a>
<br/>
<a href="dashboards">see dashboards</a> -->

<script>
    import Card from "sveltestrap/src/Card.svelte";
    import CardBody from "sveltestrap/src/CardBody.svelte";
    import CardHeader from "sveltestrap/src/CardHeader.svelte";
    import CardFooter from "sveltestrap/src/CardFooter.svelte";
    import Form from "sveltestrap/src/Form.svelte";
    import Button from "sveltestrap/src/Button.svelte";
  </script>
  
  <div style="margin-left: 30%; margin-top:15%;" class="col-lg-5">
    <Card class="shadow-lg border-0 rounded-lg mt-5" >
      <CardHeader>
        <h3 class="text-center font-weight-light my-4">Welcome!</h3>
      </CardHeader>
      <CardBody >
        <Form >
          <Button style="margin-left:39.7%" color="primary" href="datasourceDefinition-config">Add Datasource</Button><br><br>
            <Button style="margin-left:37.8%" color="primary" href="datasourceDefinition">See All Datasources</Button>
        </Form>
      </CardBody>
      <hr>
      <CardBody >
        <Form >
            <Button style="margin-left:40%" color="primary" href="dashboards-config">Add Dashboard</Button><br><br>
            <Button style="margin-left:37.8%" color="primary" href="dashboards">See All Dashboards</Button><br><br>
        </Form>
      </CardBody>
      <CardFooter class="text-center small">
        <p>Real time data analysis!</p>
      </CardFooter>
    </Card>
  </div>
  
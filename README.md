# Camunda-demo
Demo showing usage of Camunda, Java, SpringBoot, Kibana, Elaticsearch, Swagger, Docker, Ansible and Camel

<b> Architecture </b>
<p align="center">
    <img alt="Architecture" src="https://raw.githubusercontent.com/KamilWitkowski7/Camunda-demo/master/readme-images/diagram.png" />
</p>

<b>1. Build apps in this order via mvn clena install</b>
1. camel-debt-collection
2. camunda
3. event-aggregator

<b>2. Start on docker RabbitMQ</b>

Command: 
```
docker run -d --hostname my-rabbit --name rabbit-demo -p 5672:5672 -p 8090:15672 rabbitmq:3-management
```
<b>3. Configure RabbitMQ manually or via ansible</b>

<b> Configuring via ansible </b>

If you want to configure it via ansible playbook - run the script on localhost that is located in "RabbitMQ\Config RabbitMQ via Ansible" directory.

```
ansible-playbook configureRabbitmq.yml
```
If problem occurs, try this command with sudo, or with --ask-sudo-pass option.

<b> Configuring via RabbitMQ console </b>

If you want to configure it via RabbitMQ console visit http://localhost:8090/#/queues
Follow steps on screenshot that are located in "RabbitMQ\Config RabbitMQ via Rabbit Console" directory.

<b>4. Start Elasticsearch and Kibana via docker</b>

In "docker\Elasticsearch-Kibana" directory run:
```
docker-compose up
```
You can use -d option additionally if you prefer.

<b>5. Start all spring-boot aplication: Camunda, Event-aggregator and Camel-debt-collection. </b>

<b>6. Upload BPMN diagram for Camunda</b>

Diagram is located in main directory "Camunda-GitHub-example.bpmn" file.

You can upload it via "Camunda Modeller" desktop application via "Deploy Current Diagram" option.

<p align="center">
    <img alt="Camunda Modeller" src="https://raw.githubusercontent.com/KamilWitkowski7/Camunda-demo/master/readme-images/Camunda_ModeleDemo.png" />
</p>
<p align="center">
    <img alt="Camunda Modeller" src="https://raw.githubusercontent.com/KamilWitkowski7/Camunda-demo/master/readme-images/Camunda_ModelerDemo2.png" />
</p>


Name can be random. 

Url should be: http://localhost:8180/camunda/resources/engine/default/deployment/create

Authentication: None 

<b>7. Prepare test data for Elasticsearch</b>

Go to Swagger in browser: http://localhost:8087/camel-debt/swagger-ui.html#/default/REST
You can send default request once. 

<b>8. Configure Kibana</b>

In browser go to: http://localhost:5601/app/kibana#/management/kibana/objects?_g=()
Select "Import" and choose "export.json" file located in "docker\Elasticsearch-Kibana" directory.

<b>9. Watching the results </b>

The BPMN process output differ upon is policyNumber ending with digit 0 or another one.

<p align="center">
    <img alt="Swagger" src="https://raw.githubusercontent.com/KamilWitkowski7/Camunda-demo/master/readme-images/swaggerDemo.png" />
</p>

<b> Camunda </b>

To see what happens visit Camunda: http://localhost:8180/camunda (kermit/piggy)
You then go to: http://localhost:8180/camunda/app/cockpit/default/#/processes

And select 	"DebtCollectionProcess3" - now you see every process currently executing.

<p align="center">
    <img alt="Camunda" src="https://raw.githubusercontent.com/KamilWitkowski7/Camunda-demo/master/readme-images/camundaDemo.png" />
</p>

<b> Kibana </b> 

Go to: http://localhost:5601/app/kibana#/dashboards?_g=()
select ```Vindication``` and change Kibana default ```time range``` in the upper right corner to Absolute from 2018 to 2020.

The visualization on left operates on ```DueDate``` param from ```camel-debt``` Swagger, by default it is 
```<DueDate>2019-12-01</DueDate>```.
The visualization on right operates on "startTime" of process.

<p align="center">
    <img alt="Architecture" src="https://raw.githubusercontent.com/KamilWitkowski7/Camunda-demo/master/readme-images/kibanaDemo.png" />
</p>


# On Kubernetes

## What is Kubernetes
- can manage thousands of containers
- handles everything in the life cycle of containers: starting, managing, stopping
- abstracts the infrastructure details, simplifies deployment
- handles automation/failover/centralized logging/monitoring

## Architecture 
- deployed as a cluster
- has 2 main parts: master and worker nodes
![Usage](diagrams/Kubernetes.PNG?raw=true "Usage")

## Kubernetes Resources

### Pods
- multiple containers and volumes grouped within **single execution environment**- pods
- pods can be used for closely related(but seperate) process that need to share resources
- recommendation is to have 1 container per pod, because scaling an application becomes easier that way

### Services
- when dealing with microservices, 1 microservice per pod is the common recommendation
- provide a stable(does not change on restart/failure) endpoint (IP address/port) that other components within the cluster can use to communicate with Pods
- performs load balancing: distributes incoming traffic among the associated pods

### Deployment
- With deployment we can manage and scale applications
- when a deployment is created, a replica set is also created
- a replicaset makes sure that a specific number of pod replicas are running

### Volume
- a component of pod, a kind of storage
- if a container goes down, the file systems is also erased
- but the contents of a volume persists after container restart and it can be accessed by all containers across pod

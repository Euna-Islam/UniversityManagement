## Virtualization
- allows a single physical server to act like multiple computers
- creates virtual version of resources(CPU, memory, storage etc)
- these virtual resources can run separate app/os separately on a single machine

### Key components of Virtualization
#### Hypervisor
- runs on the physical host
- pools the hosts resources and allocates them to virtual machine
- there are 2 types of hypervisors: type 1 and type 2
- type 1/bare metal hypervisors install directly on top of physical server
	- provide better security and lower latency
	- example: VMware ESXI, Microsoft Hyper V, Open source KVM
- type 2 runs on host OS(installed on physical server)
	- mainly used for end user virtualization
	- have higher latency than type 1
	- example: VMWare workstation, virtualbox
#### Virtual Machine
- abstracted from physical hardware by hypervisors
- multiple VMs can run on a physical machine
- physical computer is called the host and virtual ones are guest

## Containerization
- a deployment process, packages an app with all necessary parts
- offers portability, fault tolerance
- container images contain all info to run an app
- (Open Container Image) Specification defines a standard format for creating container image
- are read only, cannot be altered by the system
- has 4 layers: 
	- infrastructure(hardware)
	- OS(linux, AWS EC2 etc)
	- container engine(intermediate image between container and OS)
	- application and dependencies(may also have a guest OS)

## Virtualization vs Containerization
- VM is best when we need to run multiple apps that need their own OS
- containers share the host machines OS Kernel, so the app needs to be compatible with host OS
- containerization is similar but improved concept of VM
- container does not have a full OS like a VM
- containers share the host system's kernel which reduces overhead and improves efficiency compared to VM
- containers provides the app exactly what resource it needs, thus preventing waste
- in container, app run independently from host OS because OS is removed from self-contained environment

## Container Orchestration
- allows automatic management of containers
- useful because manually managing large number of microservices is impossible

## On Docker
- a container runtime, build, deploy and test containerized apps on different platforms
- docker image is a set of instructions used to create containers, created using dockerfile
- container is the runtime instance of an image
- docker file contains commands for creating a docker image
- docker hub is the online repo for docker images
- docker engine hosts the containers, it's a client-server based application with 3 main components
	- server(daemon process, creates and manages image, container, network, volume)
	- client(command line interface, we use it to communicate with docker)
	- REST-API(specifies how the app interacts with server)
- [Reference](https://docs.docker.com/get-started/overview/#docker-architecture)

## On Kubernetes
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


### Extra
#### Serverless Computing
- vendor manages server infrastructure powering application
- while container gives complete control of app environment, in serverless org does not need to maintain resouces

#### Cloud Native
- app is born and reside in an cloud computing environment
- offers scalability
- containerization is one of the techs used to build cloud native apps

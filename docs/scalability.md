## Table of Contents
- [Scalability](#scalability)
- [Measuring Scalability](#measuring-scalability)
- [Horizontal and Vertical Scaling](#horizontal-and-vertical-scaling)

## Scalability
- system's ability to handle tasks as it grows in size(user, data, process, machine)
- in real world, distributed systems can grow in more than one direction at the same time
- if the number of users go up, so will the amount of data. as a result, more instances and machines may have to be added

## Measuring Scalability
- Can be done in 3 main ways: size, geography, adminstrative

#### Size
- When number of user and resource go up
- performance and efficiency should not be affected
- size scalability does not always solve complex problems
- geographical and adminstrative scalability are more subtle 
  
#### Geographical
- regardsless of the distance(physical space) between users and resources, system should function efficiently
- adding a new node, should take into account physical distance between nodes

#### Adminstrative
- when size and location of system is growing, managing it should not need significant overhead
- especially important when multiple admins/companies are managing/sharing the resources

## Horizontal and Vertical Scaling
- cloud scaling mainly happens in 2 ways: Horizontal and Vertical
- both increases processing power, storage, and networking capacity
- works primarily by using virtualization

### Vertical Scaling
- called 'scaling up', takes place on existing infrastructure
- done by adding new CPU, HDD etc
- uses concurrent programming on the single physical machine
- usually cheaper than horizontal scaling
- implementing takes a longer time
- can have longer downtime
- ideal for application with limited geographical reach
- not possible beyond the maximum capacity of a single machine
- transmits data using inter-process communication
- does not need change in logic execution

### Horizontal Scaling
- called 'scaling out', needs new infrastructure
- done by adding new node/machine
- usually needed in case of high availability(near-zero downtime)
- quicker and easier to do compared to vertical-scaling
- usually done with minimum downtime
- traffic is handled very effectively because it offers strong load balancing, distributed file systems, and clustering
- ideal for increasing geographical reach
- there is no upper limit on how many machines can be added
- transmits data over network
- to concurrently execute logic across multiple machines, sequential block of logic is broken into tiny pieces
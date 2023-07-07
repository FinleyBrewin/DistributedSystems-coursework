# DistributedSystems-coursework
The coursework for my distributed systems module. This simulates a load balancer handling job requests for nodes using a round robin system. It includes the job sender, load balancer and the node client all programmed in Java.

HOW TO USE:
1. run the load balancer application where the host IP and port will be outputted, copy it.
2. run as many node clients needed and input the Load balancer IP and port when prompted.
3. When all the node clients are waiting run the job sender application and input the LB IP and port.
4. The load balancer will start receiving jobs and distributing to all the node clients.

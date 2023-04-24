# Raymonds-Tree-Based-algorihtm---Socket-Programming-JAVA
#
This implements Raymonds tree based algorithm for deadlock prevention in a Distributed Environment.
#
The TREE structure assumed is :

                      1
                     / \
                    /   \
                   2     3
                  / \ 
                 /   \
                4     5
                
# 
Run files in the order:
Node1 -> Node 2 -> Node 3 -> Node 4 -> Node 5
#
Files:
Node1,2,3,4,5 - Nodes of Distributed system.
Node 1 - only server.
Node 2 - Server as well as client.
Node 3, Node 4,Node5 - Only client.
cl_thread - this is Our thread type. It contains our main implementation.
Critical Section - Runs CS for 10 seconds.

#
Initially Node1 is ROOT.
Holder value points to Parent Node.

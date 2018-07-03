Part of a GUI based application that allows for multiple users to chat seamlessly. This part contains the client side code. The client application connects to the remote server to report membership to the chat and then different threads for reading and writing allows for uninterrupted reading from/writing to the server. Also a simple XOR based end to end encryption system is implemented in the client side code.

To get the application only go to ChatClient/dist and download the .rar file. 
Extract its contents and then run the jar file.

Note : Before running this, get the server aplication from the Multi-Client-Chat-Application-Server-Code repository. The server needs to be already running for the client application to be able to chat, otherwise an error message will be displayed. Similar error message will be shown for wrong input in the required fields.

To connect to the server enter the IP Address of the server or "localhost" if server is running on the same machine. The port no. in the server code is hardcoded as 50000. To use any other port no. it is necessary to change the port no. assigned to the servr socket in chatserver.java line no. 190 and then rebuild the server project.

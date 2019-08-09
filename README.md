- Server:
    + View questions and choose questions for exam --> save to a table
    + View exam result
- Client:
    + take exam online/offline
    + sync question/answer when online
    + submit exam(required online)
    
- RUN:
    + java -javaagent:src/main/resources/monitor/glowroot.jar -jar target/quiz-api-0.1.0.jar
- K8s config: https://cloud.google.com/kubernetes-engine/docs/how-to/configure-backend-service

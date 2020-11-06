

This is a batch kubernetes example.   As setup you queue work, then the batch makes three pods.  Each pod
starts, dequeues and completes work until there isn't anymore.  


Docker

docker build -f docker/Dockerfile.jms -t docker-batchkub-jms . 
docker build -f docker/Dockerfile.db -t docker-batchkub-db . 
docker build -f docker/Dockerfile.setup.app -t docker-batchkub-setup-app . 
docker build -f docker/Dockerfile.worker.app -t docker-batchkub-worker-app . 


Setup base  

kubectl apply -f db.deployment.yaml         
kubectl db.service.yaml  
kubectl jms.deployment.yaml         
kubectl jms.service.yaml            

Queue the work

kubectl apply -f setup.app.deployment.yaml 
     
Do it
     
kubectl apply -f worker.app.deployment.yaml      


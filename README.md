### Depoly to AliCloud
#### 1. build backend docker image and push to repository
```
git clone -b backend https://github.com/lostemper/book-manage.git
cd path/to/backend
mvn clean package
docker build -t book-manage-backend .
docker images
#out put
REPOSITORY               TAG                  IMAGE ID       CREATED         SIZE
book-manage-backend      latest               02a5b047c36b   6 minutes ago   223MB
docker login --username=lostemper@aliyun.com registry.cn-heyuan.aliyuncs.com
#enter the password
docker tag 02a5b047c36b registry.cn-heyuan.aliyuncs.com/richard-dev/book-manage-backend:1.0
docker push registry.cn-heyuan.aliyuncs.com/richard-dev/book-manage-backend:1.0 
#out put
The push refers to repository [registry.cn-heyuan.aliyuncs.com/richard-dev/book-manage-backend]
0bc9853f34ca: Pushed
7c20ea94d59c: Pushed
d7802b8508af: Pushed
e3abdc2e9252: Pushed
eafe6e032dbd: Pushed
92a4e8a3140f: Pushed
1.0: digest: sha256:3f2a7f87d85343e257b41968483f9edb08c7fb20f22f3a299f4340b8c00e5872 size: 1573
```
#### 2.build frontend docker image and push to repository
```
git clone -b frontend https://github.com/lostemper/book-manage.git
cd path/to/frontend
docker build -t book-manage-frontend .
docker  images
#output 
REPOSITORY                                                        TAG                  IMAGE ID       CREATED             SIZE
book-manage-frontend                                              latest               9d5f75ab1059   2 minutes ago       43.3MB
book-manage-backend                                               latest               02a5b047c36b   About an hour ago   223MB
docker tag 9d5f75ab1059 registry.cn-heyuan.aliyuncs.com/richard-dev/book-manage-frontend:1.0
docker push registry.cn-heyuan.aliyuncs.com/richard-dev/book-manage-frontend:1.0 
#output
d669585e6b5c: Pushed
13c52683b537: Pushed
337b7d64083b: Pushed
cdd311f34c29: Pushed
3e8ad8bcb0ac: Pushed
74b4ff8dbbd1: Pushed
c018a48a857c: Pushed
0f73163669d4: Pushed
aedc3bda2944: Pushed
1.0: digest: sha256:0bb2c9e2cac811c8634f0c063ce56f761f2f9a6535844a126e4e93bf7f7188fc size: 2199
```

#### 3 install docker and docker compose
```
ssh  richard@xxx  
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
yum-config-manager --add-repo  http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
yum list docker-ce --showduplicates | sort -r
sudo yum makecache fast
sudo yum install docker-ce
sudo systemctl start docker
sudo systemctl enable docker
sudo  docker version
sudo usermod -aG docker $USER
newgrp docker
#when install  docker-ce also installed docker compose
docker compose --version
```
#### 4 ssh to ECS and deploy by docker compose
```
#upload docker-compose.yml file
sudo rz 
#choose  path/to/backend/docker-compose.yml
docker login --username=lostemper@aliyun.com registry.cn-heyuan.aliyuncs.com
#enter password
docker compose up -d
```


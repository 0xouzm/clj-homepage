all: build deploy

build:
		lein uberjar
		docker build -t ouzm/did-homepage:latest .
		docker push ouzm/did-homepage:latest
deploy:
		ssh -t -l ubuntu cpc_amazon "docker pull ouzm/did-homepage:latest; docker stop did-hp; docker run -p 8000:3000 --rm -d --name did-hp ouzm/did-homepage:latest; "

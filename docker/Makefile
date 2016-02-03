#
#
#

BUILD_NUMBER_FILE=build.num
BUILD_NUM := $(cat build.num)

container_deps := Dockerfile

.PHONY: container clean push $(BUILD_NUMBER_FILE)

all: container

container: $(container_deps)
	- mkdir -p WEB-INF/lib
	- mkdir -p WEB-INF/classes
	cp ../target/k8sInstanceDiscovery*.jar WEB-INF/lib
	cp ../turbine-web.war .
	jar uvf turbine-web.war WEB-INF/lib/k8sInstanceDiscovery*.jar
	jar uvf turbine-web.war WEB-INF/classes/log4j.properties
	cp $(GOPATH)/bin/gosu docker
	sudo docker build -t turbine .

run_container:
	sudo docker run -it --rm -p 9080:8080 -e POD_NAMESPACE=k8s-dev turbine
	
push:
	- sudo docker rmi registry.dstresearch.com/turbine
	sudo docker tag turbine registry.dstresearch.com/turbine
	sudo docker push registry.dstresearch.com/turbine

$(BUILD_NUMBER_FILE):
	@if ! test -f $(BUILD_NUMBER_FILE); then echo 0 > $(BUILD_NUMBER_FILE); echo setting file to zero; fi
	@echo $$(($$(cat $(BUILD_NUMBER_FILE)) + 1)) > $(BUILD_NUMBER_FILE)

clean:
	- rm -f *.jar
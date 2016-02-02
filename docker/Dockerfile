FROM travix/hystrix-turbine

MAINTAINER mchudgins

#CMD exec java -Dcom.dstresearch.turbine.namespace=$POD_NAMESPACE -Darchaius.configurationSource.additionalUrls=file:///app/config/config.properties -cp "jetty-runner.jar:/app/libs/*" org.eclipse.jetty.runner.Runner turbine-web.war

COPY turbine-web.war /app/

ADD config.properties /app/config/


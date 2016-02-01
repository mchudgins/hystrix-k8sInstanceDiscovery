/*
 * $Id$
 */
package com.dstresearch.turbine;


import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.netflix.turbine.discovery.Instance;
import com.netflix.turbine.discovery.InstanceDiscovery;

/**
 * Class that encapsulates an {@link InstanceDiscovery} implementation that uses kubernetes
 * directly to query the instances.
 *
 * longer description of the class.
 *
 * @author	Mike Hudgins <mchudgins@dstsystems.com>
 * @version	Feb 1, 2016
 * 
 */
public class K8sInstanceDiscovery implements InstanceDiscovery
	{
	private static final Logger	log	= LoggerFactory.getLogger( K8sInstanceDiscovery.class );

	/* (non-Javadoc)
	 * @see com.netflix.turbine.discovery.InstanceDiscovery#getInstanceList()
	 */
	public Collection< Instance > getInstanceList() throws Exception
		{
		
		log.debug( "getInstanceList" );
		
		Config config = new ConfigBuilder().build();
		KubernetesClient k8s = new DefaultKubernetesClient(config);
		
		log.debug( "API:  " + k8s.getApiVersion() );
		
		log.debug( "Master:  " + k8s.getMasterUrl() );
		
		log.debug( "pods:  " + k8s.inNamespace( "k8s-dev" ).pods().withName( "certs-*" ).get() );

		return null;
		}
	}

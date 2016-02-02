/*
 * $Id$
 */

package com.dstresearch.turbine;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import java.util.ArrayList;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.netflix.turbine.discovery.Instance;
import com.netflix.turbine.discovery.InstanceDiscovery;

/**
 * Class that encapsulates an {@link InstanceDiscovery} implementation that uses kubernetes directly
 * to query the instances.
 * 
 * longer description of the class.
 * 
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 * @version Feb 1, 2016
 * 
 */
public class K8sInstanceDiscovery implements InstanceDiscovery
	{
	private static final Logger	log	= LoggerFactory.getLogger( K8sInstanceDiscovery.class );
	
	private String namespace;
	
	public K8sInstanceDiscovery()
		{
		namespace = System.getenv( "POD_NAMESPACE" );
		log.info( "POD_NAMESPACE:  " + namespace );
		if ( namespace == null || namespace.length() == 0 )
			{
			namespace = System.getProperty( "com.dstresearch.turbine.namespace" );
			log.info( "com.dstresearch.turbine.namespace:  " + namespace );
			}
		}

	/*
	 * (non-Javadoc)
	 * @see com.netflix.turbine.discovery.InstanceDiscovery#getInstanceList()
	 */
	public Collection< Instance > getInstanceList() throws Exception
		{
		log.info( "getInstanceList" );

		Config config = new ConfigBuilder().build();
		KubernetesClient k8s = new DefaultKubernetesClient( config );

		log.debug( "API:  " + k8s.getApiVersion() );

		log.debug( "Master:  " + k8s.getMasterUrl() );
		
		PodList pods = k8s.inNamespace( namespace ).pods().list();
		Collection< Instance > list = new ArrayList< Instance >( pods.getItems().size() );
		
		
		for ( Pod pod : pods.getItems() )
			{
			String name = pod.getMetadata().getName();
			String ip = pod.getStatus().getPodIP();
			String genName = pod.getMetadata().getGenerateName();
			if ( genName != null && ip != null )
				{
//				log.debug( "pod:  " + name + ", " + ip + ", "
//						+ pod.getMetadata().getNamespace() + "-" + genName );
				log.debug( "pod:  " + name + ", " + ip + ", "
						+ pod.getMetadata().getNamespace() + "-"
						+ genName.substring( 0, genName.length() - 1 ) );
				String cluster = pod.getMetadata().getNamespace() + "-"
						+ genName.substring( 0, genName.length() - 1 );
				Instance obj = new Instance( ip, cluster, true );
				list.add( obj );
				}
//			log.debug( "pod dump:  " + pod );
			}

		return list;
		}
	}

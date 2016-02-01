/*
 * $Id$
 */
package com.dstresearch.turbine;


import java.util.Collection;
import org.apache.log4j.Logger;
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
	private static final Logger	log	= Logger.getLogger( K8sInstanceDiscovery.class );

	/* (non-Javadoc)
	 * @see com.netflix.turbine.discovery.InstanceDiscovery#getInstanceList()
	 */
	public Collection< Instance > getInstanceList() throws Exception
		{
		// TODO Auto-generated method stub
		return null;
		}
	}

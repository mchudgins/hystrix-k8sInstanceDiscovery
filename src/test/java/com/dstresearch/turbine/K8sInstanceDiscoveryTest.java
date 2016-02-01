/*
 * $Id$
 */
package com.dstresearch.turbine;

import java.util.Collection;
import org.apache.log4j.Logger;
import org.junit.Test;
import com.netflix.turbine.discovery.Instance;
import com.netflix.turbine.discovery.InstanceDiscovery;


/**
 * one line description.
 *
 * longer description of the class.
 *
 * @author	Mike Hudgins <mchudgins@dstsystems.com>
 * @version	Feb 1, 2016
 * 
 */
public class K8sInstanceDiscoveryTest
	{
	private static final Logger	log	= Logger.getLogger( K8sInstanceDiscoveryTest.class );
	
	@Test
	public void connectionTest()
		{
		InstanceDiscovery	id = new K8sInstanceDiscovery();
		
		try
			{
			Collection< Instance > c = id.getInstanceList();
			}
		catch ( Exception e )
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}

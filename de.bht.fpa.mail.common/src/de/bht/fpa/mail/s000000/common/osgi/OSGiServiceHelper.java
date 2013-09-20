/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.osgi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * This class provides some convenience methods for finding registered OSGi
 * services.
 * 
 * @author Siamak Haschemi
 * 
 */
public final class OSGiServiceHelper {
  private OSGiServiceHelper() {

  }

  /**
   * This method returns one service instance, or <code>null</code> if no
   * service is currently registered.
   * 
   * @param bundleContext
   *          the {@link BundleContext} of your plugin. You can obtain an
   *          instance of the {@link BundleContext} through your Activator.
   * 
   * @param clazz
   *          The {@link Class} of the service. Typically an Java Interface
   * @return service instance of the given {@link Class} type, or
   *         <code>null</code>
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static <T> T getService(BundleContext bundleContext, Class<T> clazz) {
    ServiceReference serviceReference = bundleContext.getServiceReference(clazz.getName());
    if (serviceReference == null) {
      return null;
    }

    Object service = bundleContext.getService(serviceReference);
    if (service == null) {
      return null;
    }
    return (T) service;
  }

  /**
   * This method returns a Collection of service instances, or an empty
   * Collection if no service is currently registered.
   * 
   * @param bundleContext
   *          the {@link BundleContext} of your plugin. You can obtain an
   *          instance of the {@link BundleContext} through your Activator.
   * @param clazz
   *          The {@link Class} of the service. Typically an Java Interface
   * @return Collection of service instances of the given {@link Class} type, or
   *         an empty Collection.
   */
  public static <T> Collection<T> getServices(BundleContext bundleContext, Class<T> clazz) {
    return getServices(bundleContext, clazz, null);
  }

  /**
   * This method returns a Collection of service instances, or an empty
   * Collection if no service is currently registered.
   * 
   * @param bundleContext
   *          the {@link BundleContext} of your plugin. You can obtain an
   *          instance of the {@link BundleContext} through your Activator.
   * @param clazz
   *          The {@link Class} of the service. Typically an Java Interface
   * @param filter
   *          The filter expression or null for all services. See
   *          {@link BundleContext#getServiceReferences(Class, String)}.
   * @return Collection of service instances of the given {@link Class} type, or
   *         an empty Collection.
   */
  @SuppressWarnings({ "unchecked" })
  public static <T> Collection<T> getServices(BundleContext bundleContext, Class<T> clazz, String filter) {
    Collection<ServiceReference<T>> serviceReferences;
    try {
      serviceReferences = bundleContext.getServiceReferences(clazz, null);
    } catch (InvalidSyntaxException e) {
      throw new RuntimeException(e);
    }
    if (serviceReferences.isEmpty()) {
      return Collections.EMPTY_LIST;
    }

    Collection<T> result = new ArrayList<T>(serviceReferences.size());
    for (ServiceReference<T> sr : serviceReferences) {
      result.add(bundleContext.getService(sr));
    }
    return result;
  }

  @SuppressWarnings("rawtypes")
  private static ServiceRegistration serviceRegistration = null;

  /**
   * This method registers or updates an OSGi service in the OSGi service
   * registry. Any previously registered service using this method is
   * unregistered.
   * 
   * @param bundleContext
   *          the {@link BundleContext} of your plugin. You can obtain an
   *          instance of the {@link BundleContext} through your Activator.
   * 
   * @param clazz
   *          The {@link Class} of the service. Typically an Java Interface *
   * @param service
   *          The service itself. Must be of the same type as the clazz
   *          parameter.
   */
  public static <T> void updateService(BundleContext bundleContext, Class<T> clazz, T service) {
    if (serviceRegistration != null) {
      serviceRegistration.unregister();
    }
    serviceRegistration = bundleContext.registerService(clazz, service, null);
  }
}

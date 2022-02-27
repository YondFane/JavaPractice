## 准备阶段

A类和B类循环依赖

```javascript
@Component
public class A {
    @Autowired
    private B b;

    public A() {
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
```

```java
@Component
public class B {
    @Autowired
    private A a;

    public B(){}

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
```

```java
@ComponentScan("spring.bean")
public class Main {
    public static void main(String[] args) {
        // 创建Spring容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        A springBean = (A) applicationContext.getBean("a");
        if (springBean == null) {
            return;
        }
        System.out.println(springBean);
        // Spring容器销毁
        applicationContext.close();
    }
}
```

## 调试阶段

### 1、创建Spring容器

```java
// 创建Spring容器
AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
```

### 2、AnnotationConfigApplicationContext类的构造器刷新上下文

```java
public AnnotationConfigApplicationContext(Class<?>... componentClasses) {
   this();
   register(componentClasses);
   refresh();
}
```

AnnotationConfigApplicationContext的父类GenericApplicationContext构造器中

创建了DefaultListableBeanFactory实例

```
public GenericApplicationContext() {
   this.beanFactory = new DefaultListableBeanFactory();
}
```

### 3、AbstractApplicationContext类的refresh()方法

```java
public void refresh() throws BeansException, IllegalStateException {
   synchronized (this.startupShutdownMonitor) {
      StartupStep contextRefresh = this.applicationStartup.start("spring.context.refresh");
	  // 准备此上下文以进行刷新、设置其启动日期和活动标志以及执行任何属性源的初始化
      // Prepare this context for refreshing.
      prepareRefresh();
	  
       // 告诉子类刷新内部 bean 工厂。
      // Tell the subclass to refresh the internal bean factory.
      ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
	  // 准备 bean 工厂以在此上下文中使用。
      // 配置工厂的标准上下文特征，例如上下文的 ClassLoader 和后处理器。	
      // Prepare the bean factory for use in this context.
      prepareBeanFactory(beanFactory);

      try {
         // 允许在上下文子类中对 bean 工厂进行后处理
         // 在此方法可以做一些回调操作，可以当作一个扩展功能
         // Allows post-processing of the bean factory in context subclasses.
         postProcessBeanFactory(beanFactory);

         StartupStep beanPostProcess = this.applicationStartup.start("spring.context.beans.post-process");
         // 调用在上下文中注册为 bean 的工厂处理器。
         // 执行BeanFactoryPostProcessor的postProcessBeanFactory方法 
         // Invoke factory processors registered as beans in the context.
         invokeBeanFactoryPostProcessors(beanFactory);
		 // 注册拦截 bean 创建的 bean 处理器(BeanPostProcessor)
         // Register bean processors that intercept bean creation.
         registerBeanPostProcessors(beanFactory);
         beanPostProcess.end();
		 // 初始化MessageSource（国际化）
         // Initialize message source for this context.
         initMessageSource();
		 //	为此上下文初始化事件多播器。
         // Initialize event multicaster for this context.
         initApplicationEventMulticaster();

         // Initialize other special beans in specific context subclasses.
         onRefresh();
		 // 注册监听器 	
         // Check for listener beans and register them.
         registerListeners();
	     // 实例化所有剩余的（非惰性初始化）单例。（创建bean的重要方法）		
         // Instantiate all remaining (non-lazy-init) singletons.
         finishBeanFactoryInitialization(beanFactory);

         // Last step: publish corresponding event.
         finishRefresh();
      }

      catch (BeansException ex) {
         if (logger.isWarnEnabled()) {
            logger.warn("Exception encountered during context initialization - " +
                  "cancelling refresh attempt: " + ex);
         }

         // Destroy already created singletons to avoid dangling resources.
         destroyBeans();

         // Reset 'active' flag.
         cancelRefresh(ex);

         // Propagate exception to caller.
         throw ex;
      }

      finally {
         // Reset common introspection caches in Spring's core, since we
         // might not ever need metadata for singleton beans anymore...
         resetCommonCaches();
         contextRefresh.end();
      }
   }
}
```

### 4、finishBeanFactoryInitialization(beanFactory)方法初始化需要Spring管理的单例bean

refresh()中调用finishBeanFactoryInitialization(beanFactory)方法创建需要Spring管理的单例bean并放入Spring容器。

此时A类和B类未在容器里面。

```java
protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
   // Initialize conversion service for this context.
   if (beanFactory.containsBean(CONVERSION_SERVICE_BEAN_NAME) &&
         beanFactory.isTypeMatch(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class)) {
      beanFactory.setConversionService(
            beanFactory.getBean(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class));
   }

   // Register a default embedded value resolver if no BeanFactoryPostProcessor
   // (such as a PropertySourcesPlaceholderConfigurer bean) registered any before:
   // at this point, primarily for resolution in annotation attribute values.
   if (!beanFactory.hasEmbeddedValueResolver()) {
      beanFactory.addEmbeddedValueResolver(strVal -> getEnvironment().resolvePlaceholders(strVal));
   }

   // Initialize LoadTimeWeaverAware beans early to allow for registering their transformers early.
   String[] weaverAwareNames = beanFactory.getBeanNamesForType(LoadTimeWeaverAware.class, false, false);
   for (String weaverAwareName : weaverAwareNames) {
      getBean(weaverAwareName);
   }

   // Stop using the temporary ClassLoader for type matching.
   beanFactory.setTempClassLoader(null);

   // Allow for caching all bean definition metadata, not expecting further changes.
   beanFactory.freezeConfiguration();
   // DefaultListableBeanFactory调用preInstantiateSingletons方法
   // 实例化所有剩余的（非惰性初始化）单例。
   // Instantiate all remaining (non-lazy-init) singletons.
   beanFactory.preInstantiateSingletons();
}
```

### 5、DefaultListableBeanFactory调用preInstantiateSingletons()方法实例化单例bean

```java
public void preInstantiateSingletons() throws BeansException {
   if (logger.isTraceEnabled()) {
      logger.trace("Pre-instantiating singletons in " + this);
   }
   // 获取所有bean名字	
   // Iterate over a copy to allow for init methods which in turn register new bean definitions.
   // While this may not be part of the regular factory bootstrap, it does otherwise work fine.
   List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);
   
   // 触发所有非惰性单例 bean 的初始化...
   // Trigger initialization of all non-lazy singleton beans...
   for (String beanName : beanNames) {
      // 获取RootBeanDefinition
      RootBeanDefinition bd = getMergedLocalBeanDefinition(beanName);
      // 判断不是抽象类，是单例，不需要懒加载
      if (!bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit()) {
         // 判断是否是FactoryBean
         if (isFactoryBean(beanName)) {
            Object bean = getBean(FACTORY_BEAN_PREFIX + beanName);
            if (bean instanceof FactoryBean) {
               FactoryBean<?> factory = (FactoryBean<?>) bean;
               boolean isEagerInit;
               if (System.getSecurityManager() != null && factory instanceof SmartFactoryBean) {
                  isEagerInit = AccessController.doPrivileged(
                        (PrivilegedAction<Boolean>) ((SmartFactoryBean<?>) factory)::isEagerInit,
                        getAccessControlContext());
               }
               else {
                  isEagerInit = (factory instanceof SmartFactoryBean &&
                        ((SmartFactoryBean<?>) factory).isEagerInit());
               }
               if (isEagerInit) {
                  getBean(beanName);
               }
            }
         }
         else {
            // 普通Bean走这边
            // 获取bean 
            getBean(beanName);
         }
      }
   }
   // 为所有适用的 bean 触发初始化后回调...  (实现了SmartInitializingSingleton接口的bean)
   // Trigger post-initialization callback for all applicable beans...
   for (String beanName : beanNames) {
      Object singletonInstance = getSingleton(beanName);
      if (singletonInstance instanceof SmartInitializingSingleton) {
         StartupStep smartInitialize = this.getApplicationStartup().start("spring.beans.smart-initialize")
               .tag("beanName", beanName);
         SmartInitializingSingleton smartSingleton = (SmartInitializingSingleton) singletonInstance;
         if (System.getSecurityManager() != null) {
            AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
               smartSingleton.afterSingletonsInstantiated();
               return null;
            }, getAccessControlContext());
         }
         else {
            smartSingleton.afterSingletonsInstantiated();
         }
         smartInitialize.end();
      }
   }
}
```

### 6、AbstractBeanFactory的getBean(beanName)方法

do开头的方法才是真正干活的

```java
public Object getBean(String name) throws BeansException {
   return doGetBean(name, null, null, false);
}
```

### 7、doGetBean方法返回指定 bean 的一个实例

```java
protected <T> T doGetBean(
      String name, @Nullable Class<T> requiredType, @Nullable Object[] args, boolean typeCheckOnly)
      throws BeansException {

   ......
   ......
   if (sharedInstance != null && args == null) {
      if (logger.isTraceEnabled()) {
         if (isSingletonCurrentlyInCreation(beanName)) {
            logger.trace("Returning eagerly cached instance of singleton bean '" + beanName +
                  "' that is not fully initialized yet - a consequence of a circular reference");
         }
         else {
            logger.trace("Returning cached instance of singleton bean '" + beanName + "'");
         }
      }
      beanInstance = getObjectForBeanInstance(sharedInstance, name, beanName, null);
   }

   else {
      ......
   	  ......
			
         // Create bean instance.
         if (mbd.isSingleton()) {
            // 获取单例bean 
            // 返回以给定名称注册的（原始）单例对象，如果尚未注册，则创建并注册一个新对象。参数： beanName - bean 的名称 singletonFactory 
            // - 懒惰地创建单例的 ObjectFactory，如果需要返回：注册的单例对象 
            sharedInstance = getSingleton(beanName, () -> {
               try {
                  // 创建bean对象
                  // getSingleton方法中会调用ObjectFactory的getObject方法
                  // 及调用当前代码块实现，调用createBean创建bean  
                  return createBean(beanName, mbd, args);
               }
               catch (BeansException ex) {
                  // Explicitly remove instance from singleton cache: It might have been put there
                  // eagerly by the creation process, to allow for circular reference resolution.
                  // Also remove any beans that received a temporary reference to the bean.
                  destroySingleton(beanName);
                  throw ex;
               }
            });
            beanInstance = getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
         }
		  ......
   	  	  ......
         
         
      }
      catch (BeansException ex) {
         beanCreation.tag("exception", ex.getClass().toString());
         beanCreation.tag("message", String.valueOf(ex.getMessage()));
         cleanupAfterBeanCreationFailure(beanName);
         throw ex;
      }
      finally {
         beanCreation.end();
      }
   }

   return adaptBeanInstance(name, beanInstance, requiredType);
}
```

### 8、createBean方法创建bean

AbstractAutowireCapableBeanFactory类的createBean方法

此类的中心方法：**创建 bean 实例、填充 bean 实例、应用后处理器等**。

```java
protected Object createBean(String beanName, RootBeanDefinition mbd, @Nullable Object[] args)
      throws BeanCreationException {

   ......
   ......
   try {
      // 创建bean实例
      Object beanInstance = doCreateBean(beanName, mbdToUse, args);
      if (logger.isTraceEnabled()) {
         logger.trace("Finished creating instance of bean '" + beanName + "'");
      }
      return beanInstance;
   }
   catch (BeanCreationException | ImplicitlyAppearedSingletonException ex) {
      // A previously detected exception with proper bean creation context already,
      // or illegal singleton state to be communicated up to DefaultSingletonBeanRegistry.
      throw ex;
   }
   catch (Throwable ex) {
      throw new BeanCreationException(
            mbdToUse.getResourceDescription(), beanName, "Unexpected exception during bean creation", ex);
   }
}
```

### 9、doCreateBean方法实际创建指定的bean

do开头的才是干活的方法

```java
protected Object doCreateBean(String beanName, RootBeanDefinition mbd, @Nullable Object[] args)
      throws BeanCreationException {

   // Instantiate the bean.
   BeanWrapper instanceWrapper = null;
   if (mbd.isSingleton()) {
      instanceWrapper = this.factoryBeanInstanceCache.remove(beanName);
   }
   if (instanceWrapper == null) {
      // 使用适当的实例化策略为指定的 bean 创建一个新实例：工厂方法、构造函数自动装配或简单实例化。
      // 返回bean的包装器 
      instanceWrapper = createBeanInstance(beanName, mbd, args);
   }
   // 从包装器中获取实例bean 
   Object bean = instanceWrapper.getWrappedInstance();
   Class<?> beanType = instanceWrapper.getWrappedClass();
   if (beanType != NullBean.class) {
      mbd.resolvedTargetType = beanType;
   }

   // Allow post-processors to modify the merged bean definition.
   synchronized (mbd.postProcessingLock) {
      if (!mbd.postProcessed) {
         try {
            applyMergedBeanDefinitionPostProcessors(mbd, beanType, beanName);
         }
         catch (Throwable ex) {
            throw new BeanCreationException(mbd.getResourceDescription(), beanName,
                  "Post-processing of merged bean definition failed", ex);
         }
         mbd.postProcessed = true;
      }
   }
   // 即使被 BeanFactoryAware 等生命周期接口触发，也急切地缓存单例以解析循环引用。
   // isSingletonCurrentlyInCreation判断是否在创建中 
   // 是否需要提前暴露bean的引用，解决bean循环依赖问题、AOP代理等 
   // Eagerly cache singletons to be able to resolve circular references
   // even when triggered by lifecycle interfaces like BeanFactoryAware.
   boolean earlySingletonExposure = (mbd.isSingleton() && this.allowCircularReferences &&
         isSingletonCurrentlyInCreation(beanName));
   if (earlySingletonExposure) {
      if (logger.isTraceEnabled()) {
         logger.trace("Eagerly caching bean '" + beanName +
               "' to allow for resolving potential circular references");
      }
      // 如果需要提前暴露，将Lamda表达式（ObjectFactory）加入到singletonFactories中
      // getEarlyBeanReference 获取对指定 bean 的早期访问的引用，通常用于解析循环引用。
      addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, mbd, bean));
   }

   // Initialize the bean instance.
   Object exposedObject = bean;
   try {
      // 对bean实例进行填充属性
      // 例如@Autowired指定的依赖
      populateBean(beanName, mbd, instanceWrapper);
      // 初始化给定的 bean 实例，应用工厂回调以及 init 方法和 bean 后处理器。
      exposedObject = initializeBean(beanName, exposedObject, mbd);
   }
   catch (Throwable ex) {
      if (ex instanceof BeanCreationException && beanName.equals(((BeanCreationException) ex).getBeanName())) {
         throw (BeanCreationException) ex;
      }
      else {
         throw new BeanCreationException(
               mbd.getResourceDescription(), beanName, "Initialization of bean failed", ex);
      }
   }

   if (earlySingletonExposure) {
      Object earlySingletonReference = getSingleton(beanName, false);
      if (earlySingletonReference != null) {
         if (exposedObject == bean) {
            exposedObject = earlySingletonReference;
         }
         else if (!this.allowRawInjectionDespiteWrapping && hasDependentBean(beanName)) {
            String[] dependentBeans = getDependentBeans(beanName);
            Set<String> actualDependentBeans = new LinkedHashSet<>(dependentBeans.length);
            for (String dependentBean : dependentBeans) {
               if (!removeSingletonIfCreatedForTypeCheckOnly(dependentBean)) {
                  actualDependentBeans.add(dependentBean);
               }
            }
            if (!actualDependentBeans.isEmpty()) {
               throw new BeanCurrentlyInCreationException(beanName,
                     "Bean with name '" + beanName + "' has been injected into other beans [" +
                     StringUtils.collectionToCommaDelimitedString(actualDependentBeans) +
                     "] in its raw version as part of a circular reference, but has eventually been " +
                     "wrapped. This means that said other beans do not use the final version of the " +
                     "bean. This is often the result of over-eager type matching - consider using " +
                     "'getBeanNamesForType' with the 'allowEagerInit' flag turned off, for example.");
            }
         }
      }
   }

   // Register bean as disposable.
   try {
      registerDisposableBeanIfNecessary(beanName, bean, mbd);
   }
   catch (BeanDefinitionValidationException ex) {
      throw new BeanCreationException(
            mbd.getResourceDescription(), beanName, "Invalid destruction signature", ex);
   }

   return exposedObject;
}
```

### 10、Bean属性填充populateBean方法

使用 bean 定义中的属性值填充给定 BeanWrapper 中的 bean 实例。

解决bean的依赖注入问题，如果出现循环依赖那么

populateBean——>getBean——>doGetBean——>createBean——>doCreateBean——>populateBean......

进行递归创建bean实例

### 11、Bean的初始化initializeBean方法

```java
protected Object initializeBean(String beanName, Object bean, @Nullable RootBeanDefinition mbd) {
   if (System.getSecurityManager() != null) {
      AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
         invokeAwareMethods(beanName, bean);
         return null;
      }, getAccessControlContext());
   }
   else {
      // 执行Aware接口的方法
      // 这是一个private方法
      // 只对实现了BeanNameAware BeanClassLoaderAware BeanFactoryAware接口的bean调用对应的setter方法赋值
      invokeAwareMethods(beanName, bean);
   }

   Object wrappedBean = bean;
   if (mbd == null || !mbd.isSynthetic()) {
      // 调用BeanPostProcessor的postProcessBeforeInitialization前置通知方法
      wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
   }

   try {
      // 调用bean实现InitializingBean接口的afterPropertiesSet方法
      // 以及在给定的 bean 上调用指定的自定义 init 方法
      invokeInitMethods(beanName, wrappedBean, mbd);
   }
   catch (Throwable ex) {
      throw new BeanCreationException(
            (mbd != null ? mbd.getResourceDescription() : null),
            beanName, "Invocation of init method failed", ex);
   }
   if (mbd == null || !mbd.isSynthetic()) {
      // 调用BeanPostProcessor的postProcessAfterInitialization后置通知方法 
      wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
   }

   return wrappedBean;
}
```

### 12、执行applicationContext.close()容器销毁

关闭此应用程序上下文，销毁其 bean 工厂中的所有 bean。

```java
public void close() {
   synchronized (this.startupShutdownMonitor) {
      doClose();
      // If we registered a JVM shutdown hook, we don't need it anymore now:
      // We've already explicitly closed the context.
      if (this.shutdownHook != null) {
         try {
            Runtime.getRuntime().removeShutdownHook(this.shutdownHook);
         }
         catch (IllegalStateException ex) {
            // ignore - VM is already shutting down
         }
      }
   }
}
```

### 13、doClose()执行上下文关闭

实际执行上下文关闭：发布一个 ContextClosedEvent 并销毁此应用程序上下文的 bean 工厂中的单例。由 close() 和 JVM 关闭挂钩（如果有）调用。

```java
protected void doClose() {
   // Check whether an actual close attempt is necessary...
   if (this.active.get() && this.closed.compareAndSet(false, true)) {
      if (logger.isDebugEnabled()) {
         logger.debug("Closing " + this);
      }

      if (!NativeDetector.inNativeImage()) {
         LiveBeansView.unregisterApplicationContext(this);
      }

      try {
         // 发布关闭事件
         // Publish shutdown event.
         publishEvent(new ContextClosedEvent(this));
      }
      catch (Throwable ex) {
         logger.warn("Exception thrown from ApplicationListener handling ContextClosedEvent", ex);
      }

      // Stop all Lifecycle beans, to avoid delays during individual destruction.
      if (this.lifecycleProcessor != null) {
         try {
            this.lifecycleProcessor.onClose();
         }
         catch (Throwable ex) {
            logger.warn("Exception thrown from LifecycleProcessor on context close", ex);
         }
      }
	  // 销毁上下文的 BeanFactory 中所有缓存的单例。
      // 执行bean实现DisposableBean接口的destroy方法
      // 以及在给定的 bean 上调用指定的自定义 destroy 方法
      // Destroy all cached singletons in the context's BeanFactory.
      destroyBeans();

      // Close the state of this context itself.
      closeBeanFactory();

      // Let subclasses do some final clean-up if they wish...
      onClose();

      // Reset local application listeners to pre-refresh state.
      if (this.earlyApplicationListeners != null) {
         this.applicationListeners.clear();
         this.applicationListeners.addAll(this.earlyApplicationListeners);
      }

      // Switch to inactive.
      this.active.set(false);
   }
}
```

## 总结

**Bean的创建过程（生命周期）**

#### 1、Bean的实例化

通过反射调用构造方法进行实例化或工厂方法进行实例化

#### 2、Bean的初始化

1、调用bean实现Aware接口的setter方法（回调）

2、调用BeanPostProcessor的前置通知方法postProcessBeforeInitialization

3、调用Bean实现InitializingBean接口的afterPropertiesSet方法

4、调用Bean指定的自定义 init 方法

5、调用BeanPostProcessor的后置通知方法postProcessAfterInitialization

#### 3、Bean的使用

通过spring容器进行getBean获取使用

#### 4、Bean的销毁

1、调用bean实现DisposableBean接口的destroy方法

2、 调用 bean 上调用指定的自定义 destroy 方法



**其中还有很多细节的地方没有深究**








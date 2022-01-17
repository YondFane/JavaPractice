# Bean生命周期
BeanFactoryPostProcessor--postProcessBeanFactory()执行
InstantiationAwareBeanPostProcessor--postProcessBeforeInstantiation()方法执行
SpringBean构造方法()执行
InstantiationAwareBeanPostProcessor--postProcessAfterInstantiation()方法执行
InstantiationAwareBeanPostProcessor--postProcessProperties()方法执行
SpringBean--id属性注入
BeanNameAware--setBeanName()方法执行
BeanFactoryAware--setBeanFactory()方法执行
BeanPostProcessor--postProcessBeforeInitialization()方法执行
InitializingBean--afterPropertiesSet()方法执行
BeanPostProcessor--postProcessAfterInitialization()方法执行
DisposableBean--destroy()方法执行
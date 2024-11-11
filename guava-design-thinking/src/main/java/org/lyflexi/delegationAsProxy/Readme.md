ForwardingCollection类注释如下：

>A collection which forwards all its method calls to another collection. Subclasses should override one or more methods to modify the behavior of the backing collection as desired per the decorator pattern .

释意：将其所有方法调用转发给另一个集合的集合。子类应该重写一个或多个方法，以便按照装饰器模式a的要求修改后备集合的行为。

>Warning: The methods of ForwardingCollection forward indiscriminately to the methods of the delegate. For example, overriding add alone will not change the behavior of addAll, which can lead to unexpected behavior. In this case, you should override addAll as well, either providing your own implementation, or delegating to the provided standardAddAll method.

释意：警告，ForwardingCollection的方法不加区分地转发给委托的方法。例如，单独重写add不会改变addAll的行为，这会导致意外的行为。

在这种情况下，您也应该覆盖addALl，或者提供您自己的实现，或者委托给提供的standardAddALL方法。
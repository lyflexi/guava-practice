package org.lyflexi.delegationAsProxy;

import com.google.common.collect.ForwardingCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @Description:
 * @Author: lyflexi
 * @project: guava-practice
 * @Date: 2024/11/11 14:56
 */

/**
 * 代理设计模式（委托）
 * @param <E>
 */
public class LoggingCollection<E> extends ForwardingCollection<E> {
    private static final Logger log = LoggerFactory.getLogger(LoggingCollection.class);
    private final Collection<E> delegate;

    public LoggingCollection(Collection<E> delegate) {
        this.delegate = delegate;
    }

    /**
     * ForwardingCollection 类实现了 Collection 接口中的所有方法，
     * 但是在ForwardingCollection得每个方法中，通过调用 delegate() 方法来获取被包装的集合对象，并将操作委托给它。
     * @return
     */
    @Override
    protected Collection<E> delegate() {
        return delegate;
    }

    // 可以添加额外的方法或者覆盖已有方法来添加日志功能
    @Override
    public boolean remove(Object object) {
        log.info("LoggingCollection正在移除元素：{}",object);
        return super.remove(object);
    }
}
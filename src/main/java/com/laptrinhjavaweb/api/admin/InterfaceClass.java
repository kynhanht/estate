package com.laptrinhjavaweb.api.admin;

import antlr.collections.List;
import com.sun.el.stream.Stream;
import org.hibernate.mapping.Set;

import java.util.Map;
import java.util.Queue;

public interface InterfaceClass extends List, Map {

    default void test2(){}
}

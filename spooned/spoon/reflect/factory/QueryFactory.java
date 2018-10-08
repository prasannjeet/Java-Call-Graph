/**
 * Copyright (C) 2006-2018 INRIA and contributors
 * Spoon - http://spoon.gforge.inria.fr/
 *
 * This software is governed by the CeCILL-C License under French law and
 * abiding by the rules of distribution of free software. You can use, modify
 * and/or redistribute the software under the terms of the CeCILL-C license as
 * circulated by CEA, CNRS and INRIA at http://www.cecill.info.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the CeCILL-C License for more details.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL-C license and that you accept its terms.
 */
package spoon.reflect.factory;


/**
 * A factory to create some queries on the Spoon metamodel.
 */
public class QueryFactory extends spoon.reflect.factory.SubFactory {
    /**
     * Creates the evaluation factory.
     */
    public QueryFactory(spoon.reflect.factory.Factory factory) {
        super(factory);
    }

    /**
     * Creates a unbound query. Use {@link CtQuery#setInput(Object...)}
     * before {@link CtQuery#forEach(spoon.reflect.visitor.chain.CtConsumer)}
     * or {@link CtQuery#list()} is called
     */
    public spoon.reflect.visitor.chain.CtQuery createQuery() {
        return new spoon.reflect.visitor.chain.CtQueryImpl();
    }

    /**
     * Creates a bound query. Use directly
     * {@link CtQuery#forEach(spoon.reflect.visitor.chain.CtConsumer)}
     * or {@link CtQuery#list()} to evaluate the query
     */
    public spoon.reflect.visitor.chain.CtQuery createQuery(java.lang.Object input) {
        return new spoon.reflect.visitor.chain.CtQueryImpl(input);
    }

    /**
     * Creates a bound query. Use directly
     * {@link CtQuery#forEach(spoon.reflect.visitor.chain.CtConsumer)}
     * or {@link CtQuery#list()} to evaluate the query
     */
    public spoon.reflect.visitor.chain.CtQuery createQuery(java.lang.Iterable<?> inputs) {
        return new spoon.reflect.visitor.chain.CtQueryImpl().addInput(inputs);
    }

    /**
     * Creates a bound query with an optional number
     * of inputs elements to the query (see {@link CtQuery#setInput(Object...)})
     */
    public spoon.reflect.visitor.chain.CtQuery createQuery(java.lang.Object... input) {
        return new spoon.reflect.visitor.chain.CtQueryImpl(input);
    }
}


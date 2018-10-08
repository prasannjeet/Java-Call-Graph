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
 * The {@link CtPackage} sub-factory.
 */
public class PackageFactory extends spoon.reflect.factory.SubFactory {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new package sub-factory.
     *
     * @param factory
     * 		the parent factory
     */
    public PackageFactory(spoon.reflect.factory.Factory factory) {
        super(factory);
    }

    /**
     * Creates a reference to an existing package.
     * The simple name of the reference will be the FQN of the given package
     */
    public spoon.reflect.reference.CtPackageReference createReference(spoon.reflect.declaration.CtPackage pack) {
        if (pack == null) {
            throw new java.lang.IllegalArgumentException();
        }
        return createReference(pack.getQualifiedName());
    }

    /**
     * Creates a reference to a package by using its Java runtime
     * representation. The simple name of the reference will be the FQN of the given package
     *
     * @param pack
     * 		a runtime package
     * @return reference to the package
     */
    public spoon.reflect.reference.CtPackageReference createReference(java.lang.Package pack) {
        return createReference(pack.getName());
    }

    /**
     * Returns a reference on the top level package.
     */
    public spoon.reflect.reference.CtPackageReference topLevel() {
        return factory.getModel().getRootPackage().getReference();
    }

    /**
     * Creates a reference to a package. The given name has to be a fully qualified name.
     *
     * @param name
     * 		full name of the package to reference
     */
    public spoon.reflect.reference.CtPackageReference createReference(java.lang.String name) {
        spoon.reflect.reference.CtPackageReference ref = factory.Core().createPackageReference();
        ref.setSimpleName(name);
        return ref;
    }

    /**
     * Creates a new package (see also {@link #getOrCreate(String)}).
     *
     * @param parent
     * 		the parent package (can be null)
     * @param simpleName
     * 		the package's simple name (no dots)
     * @return the newly created package
     */
    public spoon.reflect.declaration.CtPackage create(spoon.reflect.declaration.CtPackage parent, java.lang.String simpleName) {
        return getOrCreate((((parent.toString()) + (spoon.reflect.declaration.CtPackage.PACKAGE_SEPARATOR)) + simpleName));
    }

    /**
     * Gets or creates a package for the unnamed module
     *
     * @param qualifiedName
     * 		the full name of the package
     */
    public spoon.reflect.declaration.CtPackage getOrCreate(java.lang.String qualifiedName) {
        return this.getOrCreate(qualifiedName, factory.getModel().getUnnamedModule());
    }

    /**
     * Gets or creates a package and make its parent the given module
     *
     * @param qualifiedName
     * 		the full name of the package
     * @param rootModule
     * 		The parent module of the package
     */
    public spoon.reflect.declaration.CtPackage getOrCreate(java.lang.String qualifiedName, spoon.reflect.declaration.CtModule rootModule) {
        if (qualifiedName.isEmpty()) {
            return rootModule.getRootPackage();
        }
        java.util.StringTokenizer token = new java.util.StringTokenizer(qualifiedName, spoon.reflect.declaration.CtPackage.PACKAGE_SEPARATOR);
        spoon.reflect.declaration.CtPackage last = rootModule.getRootPackage();
        while (token.hasMoreElements()) {
            java.lang.String name = token.nextToken();
            spoon.reflect.declaration.CtPackage next = last.getPackage(name);
            if (next == null) {
                next = factory.Core().createPackage();
                next.setSimpleName(name);
                last.addPackage(next);
            }
            last = next;
        } 
        return last;
    }

    /**
     * Gets a package.
     *
     * @param qualifiedName
     * 		the package to search
     * @return a found package or null
     */
    public spoon.reflect.declaration.CtPackage get(java.lang.String qualifiedName) {
        if (qualifiedName.contains(spoon.reflect.declaration.CtType.INNERTTYPE_SEPARATOR)) {
            throw new java.lang.RuntimeException(("Invalid package name " + qualifiedName));
        }
        java.util.StringTokenizer token = new java.util.StringTokenizer(qualifiedName, spoon.reflect.declaration.CtPackage.PACKAGE_SEPARATOR);
        spoon.reflect.declaration.CtPackage current = factory.getModel().getRootPackage();
        if (token.hasMoreElements()) {
            current = current.getPackage(token.nextToken());
            while ((token.hasMoreElements()) && (current != null)) {
                current = current.getPackage(token.nextToken());
            } 
        }
        return current;
    }

    /**
     * Gets the list of all created packages. It includes all the top-level
     * packages and their sub-packages.
     */
    public java.util.Collection<spoon.reflect.declaration.CtPackage> getAll() {
        return factory.getModel().getAllPackages();
    }

    /**
     * Return the unnamed top-level package.
     */
    public spoon.reflect.declaration.CtPackage getRootPackage() {
        return factory.getModel().getRootPackage();
    }

    private java.util.List<spoon.reflect.declaration.CtPackage> getSubPackageList(spoon.reflect.declaration.CtPackage pack) {
        java.util.List<spoon.reflect.declaration.CtPackage> packs = new java.util.ArrayList<>();
        packs.add(pack);
        for (spoon.reflect.declaration.CtPackage p : pack.getPackages()) {
            packs.addAll(getSubPackageList(p));
        }
        return packs;
    }
}


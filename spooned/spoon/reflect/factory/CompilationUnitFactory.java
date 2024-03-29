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
 * A factory to create some evaluation utilities on the Spoon metamodel.
 */
public class CompilationUnitFactory extends spoon.reflect.factory.SubFactory {
    /**
     * Creates the evaluation factory.
     */
    public CompilationUnitFactory(spoon.reflect.factory.Factory factory) {
        super(factory);
    }

    private transient java.util.Map<java.lang.String, spoon.reflect.cu.CompilationUnit> cachedCompilationUnits = new java.util.TreeMap<>();

    /**
     * Gets the compilation unit map.
     *
     * @return a map (path -&gt; {@link CompilationUnit})
     */
    public java.util.Map<java.lang.String, spoon.reflect.cu.CompilationUnit> getMap() {
        return cachedCompilationUnits;
    }

    /**
     * Creates a compilation unit with no associated files.
     */
    public spoon.reflect.cu.CompilationUnit create() {
        return factory.Core().createCompilationUnit();
    }

    public spoon.reflect.cu.CompilationUnit getOrCreate(spoon.reflect.declaration.CtPackage ctPackage) {
        if ((ctPackage.getPosition().getCompilationUnit()) != null) {
            return ctPackage.getPosition().getCompilationUnit();
        }else {
            spoon.reflect.declaration.CtModule module;
            if ((factory.getEnvironment().getComplianceLevel()) > 8) {
                module = ctPackage.getParent(spoon.reflect.declaration.CtModule.class);
            }else {
                module = null;
            }
            java.io.File file = this.factory.getEnvironment().getOutputDestinationHandler().getOutputPath(module, ctPackage, null).toFile();
            try {
                java.lang.String path = file.getCanonicalPath();
                spoon.reflect.cu.CompilationUnit result = this.getOrCreate(path);
                result.setDeclaredPackage(ctPackage);
                ctPackage.setPosition(this.factory.createPartialSourcePosition(result));
                return result;
            } catch (java.io.IOException e) {
                throw new spoon.SpoonException(("Cannot get path for file: " + (file.getAbsolutePath())), e);
            }
        }
    }

    public spoon.reflect.cu.CompilationUnit getOrCreate(spoon.reflect.declaration.CtType type) {
        if (type == null) {
            return null;
        }
        if ((type.getPosition().getCompilationUnit()) != null) {
            return type.getPosition().getCompilationUnit();
        }
        if (type.isTopLevel()) {
            spoon.reflect.declaration.CtModule module;
            if (((type.getPackage()) != null) && ((factory.getEnvironment().getComplianceLevel()) > 8)) {
                module = type.getPackage().getParent(spoon.reflect.declaration.CtModule.class);
            }else {
                module = null;
            }
            java.io.File file = this.factory.getEnvironment().getOutputDestinationHandler().getOutputPath(module, type.getPackage(), type).toFile();
            try {
                java.lang.String path = file.getCanonicalPath();
                spoon.reflect.cu.CompilationUnit result = this.getOrCreate(path);
                result.setDeclaredPackage(type.getPackage());
                result.addDeclaredType(type);
                type.setPosition(this.factory.createPartialSourcePosition(result));
                return result;
            } catch (java.io.IOException e) {
                throw new spoon.SpoonException(("Cannot get path for file: " + (file.getAbsolutePath())), e);
            }
        }else {
            return getOrCreate(type.getTopLevelType());
        }
    }

    public spoon.reflect.cu.CompilationUnit getOrCreate(spoon.reflect.declaration.CtModule module) {
        if ((module.getPosition().getCompilationUnit()) != null) {
            return module.getPosition().getCompilationUnit();
        }else {
            java.io.File file = this.factory.getEnvironment().getOutputDestinationHandler().getOutputPath(module, null, null).toFile();
            try {
                java.lang.String path = file.getCanonicalPath();
                spoon.reflect.cu.CompilationUnit result = this.getOrCreate(path);
                result.setDeclaredModule(module);
                module.setPosition(this.factory.createPartialSourcePosition(result));
                return result;
            } catch (java.io.IOException e) {
                throw new spoon.SpoonException(("Cannot get path for file: " + (file.getAbsolutePath())), e);
            }
        }
    }

    /**
     * Creates or gets a compilation unit for a given file path.
     */
    public spoon.reflect.cu.CompilationUnit getOrCreate(java.lang.String filePath) {
        spoon.reflect.cu.CompilationUnit cu = cachedCompilationUnits.get(filePath);
        if (cu == null) {
            if (filePath.startsWith(spoon.support.compiler.jdt.JDTSnippetCompiler.SNIPPET_FILENAME_PREFIX)) {
                cu = factory.Core().createCompilationUnit();
                // put the virtual compilation unit of code snippet into cache too, so the JDTCommentBuilder can found it
                cachedCompilationUnits.put(filePath, cu);
                return cu;
            }
            cu = factory.Core().createCompilationUnit();
            if (!(filePath.equals(spoon.support.compiler.VirtualFile.VIRTUAL_FILE_NAME))) {
                cu.setFile(new java.io.File(filePath));
            }
            cachedCompilationUnits.put(filePath, cu);
        }
        return cu;
    }

    /**
     * Removes compilation unit from the cache and returns it
     * Used by JDTSnippetCompiler to remove processed snippet from the cache
     *
     * @param filePath
     * 		
     * @return a cached compilation unit or null
     */
    public spoon.reflect.cu.CompilationUnit removeFromCache(java.lang.String filePath) {
        return cachedCompilationUnits.remove(filePath);
    }
}


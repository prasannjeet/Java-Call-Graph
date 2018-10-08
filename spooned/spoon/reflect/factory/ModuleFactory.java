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


public class ModuleFactory extends spoon.reflect.factory.SubFactory {
    public static class CtUnnamedModule extends spoon.support.reflect.declaration.CtModuleImpl {
        final java.util.Set<spoon.reflect.declaration.CtModule> allModules = new java.util.HashSet<>();

        final spoon.reflect.declaration.CtElement parent = new spoon.support.reflect.declaration.CtElementImpl() {
            @java.lang.Override
            public void accept(spoon.reflect.visitor.CtVisitor visitor) {
            }

            @java.lang.Override
            public spoon.reflect.declaration.CtElement getParent() throws spoon.reflect.declaration.ParentNotInitializedException {
                return null;
            }

            @java.lang.Override
            public spoon.reflect.factory.Factory getFactory() {
                return spoon.reflect.factory.ModuleFactory.CtUnnamedModule.this.getFactory();
            }
        };

        {
            this.setSimpleName(spoon.reflect.declaration.CtModule.TOP_LEVEL_MODULE_NAME);
            this.addModule(this);
        }

        public boolean addModule(spoon.reflect.declaration.CtModule module) {
            return this.allModules.add(module);
        }

        public spoon.reflect.declaration.CtModule getModule(java.lang.String name) {
            for (spoon.reflect.declaration.CtModule module : this.allModules) {
                if (module.getSimpleName().equals(name)) {
                    return module;
                }
            }
            return null;
        }

        public java.util.Collection<spoon.reflect.declaration.CtModule> getAllModules() {
            return java.util.Collections.unmodifiableCollection(allModules);
        }

        @java.lang.Override
        public <T extends spoon.reflect.declaration.CtNamedElement> T setSimpleName(java.lang.String name) {
            if (name == null) {
                return ((T) (this));
            }
            if (name.equals(spoon.reflect.declaration.CtModule.TOP_LEVEL_MODULE_NAME)) {
                return super.setSimpleName(name);
            }
            return ((T) (this));
        }

        @java.lang.Override
        public java.lang.String toString() {
            return spoon.reflect.declaration.CtModule.TOP_LEVEL_MODULE_NAME;
        }

        @java.lang.Override
        public void accept(spoon.reflect.visitor.CtVisitor visitor) {
            visitor.visitCtModule(this);
        }

        @java.lang.Override
        public spoon.reflect.declaration.CtElement getParent() {
            return this.parent;
        }
    }

    public ModuleFactory(spoon.reflect.factory.Factory factory) {
        super(factory);
    }

    public spoon.reflect.factory.ModuleFactory.CtUnnamedModule getUnnamedModule() {
        return ((spoon.reflect.factory.ModuleFactory.CtUnnamedModule) (factory.getModel().getUnnamedModule()));
    }

    public java.util.Collection<spoon.reflect.declaration.CtModule> getAllModules() {
        return getUnnamedModule().getAllModules();
    }

    public spoon.reflect.declaration.CtModule getModule(java.lang.String moduleName) {
        return getUnnamedModule().getModule(moduleName);
    }

    public spoon.reflect.declaration.CtModule getOrCreate(java.lang.String moduleName) {
        if ((moduleName == null) || (moduleName.isEmpty())) {
            return getUnnamedModule();
        }
        spoon.reflect.declaration.CtModule ctModule = getUnnamedModule().getModule(moduleName);
        if (ctModule == null) {
            ctModule = factory.Core().createModule().setSimpleName(moduleName);
            ctModule.setRootPackage(new spoon.reflect.CtModelImpl.CtRootPackage());
            ctModule.setParent(getUnnamedModule());
        }
        return ctModule;
    }

    public spoon.reflect.reference.CtModuleReference createReference(spoon.reflect.declaration.CtModule module) {
        return factory.Core().createModuleReference().setSimpleName(module.getSimpleName());
    }

    public spoon.reflect.declaration.CtModuleRequirement createModuleRequirement(spoon.reflect.reference.CtModuleReference moduleReference) {
        return factory.Core().createModuleRequirement().setModuleReference(moduleReference);
    }

    public spoon.reflect.declaration.CtPackageExport createPackageExport(spoon.reflect.reference.CtPackageReference ctPackageReference) {
        return factory.Core().createPackageExport().setPackageReference(ctPackageReference);
    }

    public spoon.reflect.declaration.CtProvidedService createProvidedService(spoon.reflect.reference.CtTypeReference typeReference) {
        return factory.Core().createProvidedService().setServiceType(typeReference);
    }

    public spoon.reflect.declaration.CtUsedService createUsedService(spoon.reflect.reference.CtTypeReference typeReference) {
        return factory.Core().createUsedService().setServiceType(typeReference);
    }
}


package org.eclipse.reqcycle.predicates.persistance.util;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.predicates.persistance.PredicatesConfFactory;
import org.eclipse.reqcycle.predicates.persistance.api.PredicatesConf;
import org.eclipse.ziggurat.configuration.IConfigurationManager;
import org.eclipse.ziggurat.configuration.impl.ConfigurationManagerImpl;

/**
 * This class is an utility that helps to manage predicates configuration (persistence, removal, and so forth ...)
 * 
 * @author Papa Issa DIAKHATE
 */
public class PredicatesConfManager {

    /**
     * The id of the configuration file which contains the name of the stored predicates.
     */
    public static final String PREDICATES_ENTRIES_CONF_ID = "org.eclipse.reqcycle.predicates.entries";

    protected ResourceSet      rs;

    public PredicatesConfManager() {
        this(null);
    }

    public PredicatesConfManager(ResourceSet rs) {
        if (rs == null) {
            rs = new ResourceSetImpl();
        }
        this.rs = rs;
    }

    /**
     * Stores a new predicate into the workspace.
     * 
     * @param predicateName
     * @param predicate
     * 
     * @return <code>true</code> if the persisting operation is done correctly, <code>false</code> otherwise.
     * 
     * @see #isPredicateNameAlreadyUsed(String)
     */
    public boolean storePredicate(final String predicateName, final IPredicate predicate) {
        boolean added = false;
        try {
            predicate.setDisplayName(predicateName);
            PredicatesConf predicatesConf = this.getConf();
            if (predicatesConf == null) {
                predicatesConf = PredicatesConfFactory.eINSTANCE.createPredicatesConf();
            }
            added = predicatesConf.getPredicates().add(predicate);
            getConfManager().saveConfiguration(predicatesConf, null, null, PREDICATES_ENTRIES_CONF_ID, rs);

        } catch (IOException ioe) {
            // TODO log ...
            ioe.printStackTrace();
            added = false;
        }
        return added;
    }

    /**
     * @return The stored predicates.
     */
    public Collection<IPredicate> getStoredPredicates() {
        PredicatesConf conf = this.getConf();
        if (conf == null) return Collections.emptyList();
        return conf.getPredicates();
    }

    /**
     * @param predicateName
     * @return <code>true</code> if the name is already used by another persisted predicate, <code>false</code>
     *         otherwise.
     */
    public boolean isPredicateNameAlreadyUsed(final String predicateName) {
        boolean alreadyUsed = false;
        PredicatesConf conf = this.getConf();
        if (predicateName != null && conf != null) {
            for (IPredicate p : conf.getPredicates()) {
                if (predicateName.equals(p.getDisplayName())) {
                    alreadyUsed = true;
                    break;
                }
            }
        }
        return alreadyUsed;
    }

    /**
     * @param predicateName - The name of the predicate to retrieve.
     * @return The stored predicate having the specified name or <code>null</code> if not found or if the specified
     *         predicate's name is <code>null</code>.
     */
    public IPredicate getPredicateByName(final String predicateName) {
        if (predicateName != null) {
            for (IPredicate p : getStoredPredicates()) {
                if (predicateName.equals(p.getDisplayName())) {
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * Remove the first stored predicate having the specified name.
     * 
     * @return <code>true</code> if the removal operation is done correctly, <code>false</code> otherwise.
     * 
     * @param predicateName - The name of the predicate to remove.
     */
    public boolean removeStoredPredicate(String predicateName) {
        boolean removed = false;
        PredicatesConf conf = this.getConf();
        if (predicateName != null && conf != null) {
            for (IPredicate p : conf.getPredicates()) {
                if (predicateName.equals(p.getDisplayName())) {
                    removed = conf.getPredicates().remove(p);
                    if (removed) {
                        try {
                            getConfManager().saveConfiguration(conf, null, null, PREDICATES_ENTRIES_CONF_ID, rs);
                        } catch (IOException e) {
                            // TODO : log ...
                            e.printStackTrace();
                            removed = false;
                        }
                        break;
                    }
                }
            }
        }
        return removed;
    }

    private PredicatesConf getConf() {
        return (PredicatesConf) getConfManager().getConfiguration(null, null, PREDICATES_ENTRIES_CONF_ID, rs);
    }

    private IConfigurationManager getConfManager() {
        return new ConfigurationManagerImpl();
    }

}

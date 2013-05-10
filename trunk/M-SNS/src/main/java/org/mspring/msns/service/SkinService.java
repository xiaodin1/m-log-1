/**
 * 
 */
package org.mspring.msns.service;

import java.util.List;

import org.mspring.msns.entity.Skin;

/**
 * @author Gao Youbo
 * @since 2012-7-24
 * @Description 
 * @TODO 
 */
public interface SkinService {
    String getCurrentSkinFolder();
    
    Skin getSkinByFolder(String folder);
    
    List<Skin> scrnSkin();
}

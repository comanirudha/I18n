/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.broadleafcommerce.core.order.domain;

import org.broadleafcommerce.common.locale.domain.Locale;
import org.broadleafcommerce.common.locale.util.LocaleUtil;
import org.broadleafcommerce.common.web.BroadleafRequestContext;

import com.broadleafcommerce.i18n.domain.order.FulfillmentOptionTranslation;
import com.broadleafcommerce.i18n.domain.order.I18NFulfillmentOption;

import javax.persistence.Embedded;

import java.util.Map;



/**
 * @author Andre Azzolini (apazzolini)
 */
public class MyFulfillmentOptionImpl extends FulfillmentOptionImpl implements I18NFulfillmentOption {
    private static final long serialVersionUID = -1849002347743308131L;
    
    @Embedded
    protected I18NFulfillmentOption i18nExtension;

    public Map<String, FulfillmentOptionTranslation> getTranslations() {
        return i18nExtension.getTranslations();
    }

    public void setTranslations(Map<String, FulfillmentOptionTranslation> translations) {
        i18nExtension.setTranslations(translations);
    }
    
    @Override
    public String getLongDescription() {
        if (getTranslations() != null && BroadleafRequestContext.hasLocale())  {
            Locale locale = BroadleafRequestContext.getBroadleafRequestContext().getLocale();

            // Search for translation based on locale
            String localeCode = locale.getLocaleCode();
            if (localeCode != null) {
                FulfillmentOptionTranslation translation = getTranslations().get(localeCode);
                if (translation != null && translation.getLongDescription() != null) {
                    return translation.getLongDescription();
                }
            }

            // try just the language
            String languageCode = LocaleUtil.findLanguageCode(locale);
            if (languageCode != null && ! localeCode.equals(languageCode)) {
                FulfillmentOptionTranslation translation = getTranslations().get(languageCode);
                if (translation != null && translation.getLongDescription() != null) {
                    return translation.getLongDescription();
                }
            }
        }
        return longDescription;
    }
    
    @Override
    public String getName() {
        if (getTranslations() != null && BroadleafRequestContext.hasLocale())  {
            Locale locale = BroadleafRequestContext.getBroadleafRequestContext().getLocale();

            // Search for translation based on locale
            String localeCode = locale.getLocaleCode();
            if (localeCode != null) {
                FulfillmentOptionTranslation translation = getTranslations().get(localeCode);
                if (translation != null && translation.getName() != null) {
                    return translation.getName();
                }
            }

            // try just the language
            String languageCode = LocaleUtil.findLanguageCode(locale);
            if (languageCode != null && ! localeCode.equals(languageCode)) {
                FulfillmentOptionTranslation translation = getTranslations().get(languageCode);
                if (translation != null && translation.getName() != null) {
                    return translation.getName();
                }
            }
        }
        return name;
    }
    
}

package es.upm.miw.apaw.theme.api.resources;

import java.util.List;

import es.upm.miw.apaw.theme.api.controllers.ThemeController;
import es.upm.miw.apaw.theme.api.dtos.ThemeDto;
import es.upm.miw.apaw.theme.api.dtos.ThemeVoteDto;
import es.upm.miw.apaw.theme.api.resources.exceptions.ThemeFieldInvalidException;
import es.upm.miw.apaw.theme.api.resources.exceptions.ThemeIdNotFoundException;

public class ThemeResource {

    public static final String THEMES = "themes";

    public static final String THEMES_ID_ID_OVERAGE = "overage";
    
    public static final String THEMES_ID_ID_VOTE = "vote";

    // GET **/themes
    public List<ThemeDto> themeList() {
        return new ThemeController().themeList();
    }

    // POST **/themes body="themeName"
    public void createTheme(String themeName) throws ThemeFieldInvalidException {
        this.validateField(themeName);
        new ThemeController().createTheme(themeName);
    }

    private void validateField(String field) throws ThemeFieldInvalidException {
        if (field == null || field.isEmpty()) {
            throw new ThemeFieldInvalidException(field);
        }
    }

    // GET **/themes/{id}/overage
    public Double themeOverage(int themeId) throws ThemeIdNotFoundException {
        if (!new ThemeController().existThemeId(themeId)) {
            throw new ThemeIdNotFoundException(Integer.toString(themeId));
        } else {
            return new ThemeController().themeOverage(themeId);
        }
    }
    
    // GET **/themes/{id}/vote
    public ThemeVoteDto themeVote(int themeId)  throws ThemeIdNotFoundException {
        if (!new ThemeController().existThemeId(themeId)) {
            throw new ThemeIdNotFoundException(Integer.toString(themeId));
        } else {
            return new ThemeController().themeVote(themeId);
        }        
    }
}

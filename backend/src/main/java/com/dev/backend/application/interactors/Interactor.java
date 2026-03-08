package com.dev.backend.application.interactors;

import com.dev.backend.application.exceptions.ApplicationException;

public interface Interactor<InputDTO, OutputDTO> {
    public OutputDTO execute(InputDTO data) throws ApplicationException;
}

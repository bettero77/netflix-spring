package com.practicetask.netflixandspringbaby.services;

import com.practicetask.netflixandspringbaby.domain.Workspace;
import com.practicetask.netflixandspringbaby.exceptions.NoWorkspaceFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.practicetask.netflixandspringbaby.domain.OSFamily.*;
import static java.lang.String.format;
import static java.util.UUID.randomUUID;

@Service
public class WorkplaceService {

    private final List<Workspace> workspaces = newArrayList(
            new Workspace(1, 1, 1, randomUUID().toString(), WINDOWS),
            new Workspace(2, 1, 2, randomUUID().toString(), WINDOWS),
            new Workspace(3, 1, 3, randomUUID().toString(), WINDOWS),
            new Workspace(4, 1, 4, randomUUID().toString(), OSX),
            new Workspace(5, 1, 5, randomUUID().toString(), OSX),
            new Workspace(6, 1, 6, randomUUID().toString(), OSX),
            new Workspace(7, 1, 7, randomUUID().toString(), WINDOWS),
            new Workspace(8, 2, 1, randomUUID().toString(), WINDOWS),
            new Workspace(9, 2, 2, randomUUID().toString(), WINDOWS),
            new Workspace(10, 2, 3, randomUUID().toString(), OSX),
            new Workspace(11, 2, 4, randomUUID().toString(), OSX),
            new Workspace(12, 2, 5, randomUUID().toString(), WINDOWS),
            new Workspace(13, 2, 6, randomUUID().toString(), WINDOWS),
            new Workspace(14, 2, 7, randomUUID().toString(), LINUX),
            new Workspace(15, 2, 9, randomUUID().toString(), LINUX)
    );

    public Workspace findWorkspace(String id) {
        return workspaces.stream()
                .filter(w -> w.getId().equals(Integer.parseInt(id)))
                .findFirst()
                .orElseThrow(() -> new NoWorkspaceFoundException(format("No workspace found with id: %s", id)));
    }
}

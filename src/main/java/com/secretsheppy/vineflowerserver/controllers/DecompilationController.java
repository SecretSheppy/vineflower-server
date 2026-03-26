package com.secretsheppy.vineflowerserver.controllers;

import com.secretsheppy.vineflowerserver.responses.DecompilationResponse;
import com.secretsheppy.vineflowerserver.savers.InMemoryResultSaver;
import com.secretsheppy.vineflowerserver.exceptions.ApiException;
import com.secretsheppy.vineflowerserver.savers.ResultSaver;
import org.jetbrains.java.decompiler.api.Decompiler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Map;

@RestController
public class DecompilationController {

    /**
     * A recreation of the vineflower cli as a url. Source files, libraries and the destination file or directory must
     * all be specified with specific parameter names. The library and destination parameters are completely optional.
     * If no destination is specified then the resulting decompilation will be returned in the http response JSON. All
     * <a href="https://vineflower.org/usage/#base-decompiler-options">base decompiler options</a> can be added as
     * additional parameters using their exact cli name without any dash prefixes.
     *
     * @param libraries The (optional) list of library locations.
     * @param sources The list of sources to decompile.
     * @param destination The (optional) export location or file.
     * @param options The (optional) base decompiler options.
     * @return The JSON results of the decompilation.
     */
    @GetMapping("/vineflower")
    public ResponseEntity<DecompilationResponse> vineflower(
            @RequestParam(name = "library", required = false) File[] libraries,
            @RequestParam(name = "source") File[] sources,
            @RequestParam(required = false) File destination,
            @RequestParam Map<String, String> options) {
        if (libraries == null) {
            libraries = new File[0];
        }
        ResultSaver resultSaver = new ResultSaver();
        Decompiler.Builder builder = new Decompiler.Builder()
                .inputs(sources)
                .output(resultSaver.IResultSaver(destination))
                .libraries(libraries);
        options.forEach(builder::option);
        try {
            builder.build().decompile();
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(new DecompilationResponse(destination,
                resultSaver.getInMemoryResultSaver().getResults()));
    }
}

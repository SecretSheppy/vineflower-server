package com.secretsheppy.vineflowerserver.responses;

import java.io.File;
import java.util.Map;

public record DecompilationResponse(File destination, Map<String, String> output) {}

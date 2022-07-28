package com.kiseru.brainfuck.exception

import java.lang.RuntimeException

class IllegalSymbolToProcess(message: String) : RuntimeException(message)
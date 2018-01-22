VTABLE(_Main) {
    <empty>
    Main
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T0 = 4
    parm _T0
    _T1 =  call _Alloc
    _T2 = VTBL <_Main>
    *(_T1 + 0) = _T2
    return _T1
}

FUNCTION(main) {
memo ''
main:
    _T3 = 3
    _T4 = 0
    _T5 = (_T3 < _T4)
    if (_T5 == 0) branch _L10
    _T6 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T6
    call _PrintString
    call _Halt
_L10:
    _T7 = 4
    _T8 = (_T7 * _T3)
    _T9 = (_T7 + _T8)
    parm _T9
    _T10 =  call _Alloc
    *(_T10 + 0) = _T3
    _T11 = 0
    _T10 = (_T10 + _T9)
_L11:
    _T9 = (_T9 - _T7)
    if (_T9 == 0) branch _L12
    _T10 = (_T10 - _T7)
    *(_T10 + 0) = _T11
    branch _L11
_L12:
    _T12 = 3
    _T13 = 0
    _T14 = (_T12 < _T13)
    if (_T14 == 0) branch _L13
    _T15 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T15
    call _PrintString
    call _Halt
_L13:
    _T16 = 4
    _T17 = (_T16 * _T12)
    _T18 = (_T16 + _T17)
    parm _T18
    _T19 =  call _Alloc
    *(_T19 + 0) = _T12
    _T20 = 0
    _T19 = (_T19 + _T18)
_L14:
    _T18 = (_T18 - _T16)
    if (_T18 == 0) branch _L15
    _T19 = (_T19 - _T16)
    *(_T19 + 0) = _T20
    branch _L14
_L15:
    _T21 = 3
    _T22 = 0
    _T23 = (_T21 < _T22)
    if (_T23 == 0) branch _L16
    _T24 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T24
    call _PrintString
    call _Halt
_L16:
    _T25 = 4
    _T26 = (_T25 * _T21)
    _T27 = (_T25 + _T26)
    parm _T27
    _T28 =  call _Alloc
    *(_T28 + 0) = _T21
    _T29 = 0
    _T28 = (_T28 + _T27)
_L17:
    _T27 = (_T27 - _T25)
    if (_T27 == 0) branch _L18
    _T28 = (_T28 - _T25)
    *(_T28 + 0) = _T29
    branch _L17
_L18:
    _T35 = 1
    _T30 = _T35
    parm _T30
    call _PrintInt
    _T36 = "\n"
    parm _T36
    call _PrintString
    _T37 = 3
    _T38 = 3
    _T39 = 0
    _T40 = (_T38 < _T39)
    if (_T40 == 0) branch _L19
    _T41 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T41
    call _PrintString
    call _Halt
_L19:
    _T42 = 4
    _T43 = (_T42 * _T38)
    _T44 = (_T42 + _T43)
    parm _T44
    _T45 =  call _Alloc
    *(_T45 + 0) = _T38
    _T46 = 0
    _T45 = (_T45 + _T44)
_L20:
    _T44 = (_T44 - _T42)
    if (_T44 == 0) branch _L21
    _T45 = (_T45 - _T42)
    *(_T45 + 0) = _T46
    branch _L20
_L21:
    _T47 = 0
    *(_T45 + 4) = _T47
    _T48 = 12
    *(_T45 + 8) = _T48
    _T49 = 3
    _T50 = 0
    _T51 = (_T49 < _T50)
    if (_T51 == 0) branch _L22
    _T52 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T52
    call _PrintString
    call _Halt
_L22:
    _T53 = 4
    _T54 = (_T53 * _T49)
    _T55 = (_T53 + _T54)
    parm _T55
    _T56 =  call _Alloc
    *(_T56 + 0) = _T49
    _T57 = 0
    _T56 = (_T56 + _T55)
_L23:
    _T55 = (_T55 - _T53)
    if (_T55 == 0) branch _L24
    _T56 = (_T56 - _T53)
    *(_T56 + 0) = _T57
    branch _L23
_L24:
    _T58 = 3
    _T59 = 0
    _T60 = (_T58 < _T59)
    if (_T60 == 0) branch _L25
    _T61 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T61
    call _PrintString
    call _Halt
_L25:
    _T62 = 4
    _T63 = (_T62 * _T58)
    _T64 = (_T62 + _T63)
    parm _T64
    _T65 =  call _Alloc
    *(_T65 + 0) = _T58
    _T66 = 0
    _T65 = (_T65 + _T64)
_L26:
    _T64 = (_T64 - _T62)
    if (_T64 == 0) branch _L27
    _T65 = (_T65 - _T62)
    *(_T65 + 0) = _T66
    branch _L26
_L27:
    *(_T65 + 4) = _T37
    _T67 = 0
    *(_T65 + 8) = _T67
    _T68 = *(_T65 + 4)
    _T69 = *(_T65 + 8)
    _T70 = *(_T45 + 4)
    _T71 = *(_T45 + 8)
    _T72 = (_T68 + _T70)
    _T73 = (_T69 + _T71)
    *(_T56 + 4) = _T72
    *(_T56 + 8) = _T73
    _T74 = *(_T56 + 4)
    _T75 = *(_T56 + 8)
    *(_T10 + 4) = _T74
    *(_T10 + 8) = _T75
    _T76 = *(_T10 + 4)
    parm _T76
    call _PrintInt
    _T77 = "+"
    parm _T77
    call _PrintString
    _T78 = *(_T10 + 8)
    parm _T78
    call _PrintInt
    _T79 = "j"
    parm _T79
    call _PrintString
    _T80 = "\n"
    parm _T80
    call _PrintString
    _T81 = 3
    _T82 = 3
    _T83 = 0
    _T84 = (_T82 < _T83)
    if (_T84 == 0) branch _L28
    _T85 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T85
    call _PrintString
    call _Halt
_L28:
    _T86 = 4
    _T87 = (_T86 * _T82)
    _T88 = (_T86 + _T87)
    parm _T88
    _T89 =  call _Alloc
    *(_T89 + 0) = _T82
    _T90 = 0
    _T89 = (_T89 + _T88)
_L29:
    _T88 = (_T88 - _T86)
    if (_T88 == 0) branch _L30
    _T89 = (_T89 - _T86)
    *(_T89 + 0) = _T90
    branch _L29
_L30:
    _T91 = 0
    *(_T89 + 4) = _T91
    _T92 = 45
    *(_T89 + 8) = _T92
    _T93 = 3
    _T94 = 0
    _T95 = (_T93 < _T94)
    if (_T95 == 0) branch _L31
    _T96 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T96
    call _PrintString
    call _Halt
_L31:
    _T97 = 4
    _T98 = (_T97 * _T93)
    _T99 = (_T97 + _T98)
    parm _T99
    _T100 =  call _Alloc
    *(_T100 + 0) = _T93
    _T101 = 0
    _T100 = (_T100 + _T99)
_L32:
    _T99 = (_T99 - _T97)
    if (_T99 == 0) branch _L33
    _T100 = (_T100 - _T97)
    *(_T100 + 0) = _T101
    branch _L32
_L33:
    _T102 = 3
    _T103 = 0
    _T104 = (_T102 < _T103)
    if (_T104 == 0) branch _L34
    _T105 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T105
    call _PrintString
    call _Halt
_L34:
    _T106 = 4
    _T107 = (_T106 * _T102)
    _T108 = (_T106 + _T107)
    parm _T108
    _T109 =  call _Alloc
    *(_T109 + 0) = _T102
    _T110 = 0
    _T109 = (_T109 + _T108)
_L35:
    _T108 = (_T108 - _T106)
    if (_T108 == 0) branch _L36
    _T109 = (_T109 - _T106)
    *(_T109 + 0) = _T110
    branch _L35
_L36:
    *(_T109 + 4) = _T81
    _T111 = 0
    *(_T109 + 8) = _T111
    _T112 = *(_T109 + 4)
    _T113 = *(_T109 + 8)
    _T114 = *(_T89 + 4)
    _T115 = *(_T89 + 8)
    _T116 = (_T112 + _T114)
    _T117 = (_T113 + _T115)
    *(_T100 + 4) = _T116
    *(_T100 + 8) = _T117
    _T118 = *(_T100 + 4)
    _T119 = *(_T100 + 8)
    *(_T28 + 4) = _T118
    *(_T28 + 8) = _T119
    _T120 = *(_T10 + 4)
    _T31 = _T120
    _T121 = *(_T10 + 8)
    _T32 = _T121
    parm _T31
    call _PrintInt
    parm _T32
    call _PrintInt
    _T122 = "\n"
    parm _T122
    call _PrintString
    _T123 = (_T31 + _T32)
    _T124 = 3
    _T125 = 0
    _T126 = (_T124 < _T125)
    if (_T126 == 0) branch _L37
    _T127 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T127
    call _PrintString
    call _Halt
_L37:
    _T128 = 4
    _T129 = (_T128 * _T124)
    _T130 = (_T128 + _T129)
    parm _T130
    _T131 =  call _Alloc
    *(_T131 + 0) = _T124
    _T132 = 0
    _T131 = (_T131 + _T130)
_L38:
    _T130 = (_T130 - _T128)
    if (_T130 == 0) branch _L39
    _T131 = (_T131 - _T128)
    *(_T131 + 0) = _T132
    branch _L38
_L39:
    *(_T131 + 4) = _T123
    _T133 = 0
    *(_T131 + 8) = _T133
    _T134 = *(_T131 + 4)
    _T135 = *(_T131 + 8)
    *(_T19 + 4) = _T134
    *(_T19 + 8) = _T135
    _T136 = *(_T19 + 4)
    parm _T136
    call _PrintInt
    _T137 = "+"
    parm _T137
    call _PrintString
    _T138 = *(_T19 + 8)
    parm _T138
    call _PrintInt
    _T139 = "j"
    parm _T139
    call _PrintString
    _T140 = "\n"
    parm _T140
    call _PrintString
    _T141 = 3
    _T142 = 0
    _T143 = (_T141 < _T142)
    if (_T143 == 0) branch _L40
    _T144 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T144
    call _PrintString
    call _Halt
_L40:
    _T145 = 4
    _T146 = (_T145 * _T141)
    _T147 = (_T145 + _T146)
    parm _T147
    _T148 =  call _Alloc
    *(_T148 + 0) = _T141
    _T149 = 0
    _T148 = (_T148 + _T147)
_L41:
    _T147 = (_T147 - _T145)
    if (_T147 == 0) branch _L42
    _T148 = (_T148 - _T145)
    *(_T148 + 0) = _T149
    branch _L41
_L42:
    _T150 = *(_T10 + 4)
    _T151 = *(_T10 + 8)
    _T152 = *(_T19 + 4)
    _T153 = *(_T19 + 8)
    _T154 = (_T150 + _T152)
    _T155 = (_T151 + _T153)
    *(_T148 + 4) = _T154
    *(_T148 + 8) = _T155
    _T156 = *(_T148 + 4)
    parm _T156
    call _PrintInt
    _T157 = "+"
    parm _T157
    call _PrintString
    _T158 = *(_T148 + 8)
    parm _T158
    call _PrintInt
    _T159 = "j"
    parm _T159
    call _PrintString
    _T160 = "\n"
    parm _T160
    call _PrintString
    _T161 = 3
    _T162 = 0
    _T163 = (_T161 < _T162)
    if (_T163 == 0) branch _L43
    _T164 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T164
    call _PrintString
    call _Halt
_L43:
    _T165 = 4
    _T166 = (_T165 * _T161)
    _T167 = (_T165 + _T166)
    parm _T167
    _T168 =  call _Alloc
    *(_T168 + 0) = _T161
    _T169 = 0
    _T168 = (_T168 + _T167)
_L44:
    _T167 = (_T167 - _T165)
    if (_T167 == 0) branch _L45
    _T168 = (_T168 - _T165)
    *(_T168 + 0) = _T169
    branch _L44
_L45:
    _T170 = *(_T10 + 4)
    _T171 = *(_T10 + 8)
    _T172 = *(_T19 + 4)
    _T173 = *(_T19 + 8)
    _T174 = (_T170 + _T172)
    _T175 = (_T171 + _T173)
    *(_T168 + 4) = _T174
    *(_T168 + 8) = _T175
    _T176 = *(_T168 + 4)
    parm _T176
    call _PrintInt
    _T177 = "+"
    parm _T177
    call _PrintString
    _T178 = *(_T168 + 8)
    parm _T178
    call _PrintInt
    _T179 = "j"
    parm _T179
    call _PrintString
    _T180 = *(_T28 + 4)
    parm _T180
    call _PrintInt
    _T181 = "+"
    parm _T181
    call _PrintString
    _T182 = *(_T28 + 8)
    parm _T182
    call _PrintInt
    _T183 = "j"
    parm _T183
    call _PrintString
    _T184 = "\n"
    parm _T184
    call _PrintString
    _T185 = 3
    _T186 = 0
    _T187 = (_T185 < _T186)
    if (_T187 == 0) branch _L46
    _T188 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T188
    call _PrintString
    call _Halt
_L46:
    _T189 = 4
    _T190 = (_T189 * _T185)
    _T191 = (_T189 + _T190)
    parm _T191
    _T192 =  call _Alloc
    *(_T192 + 0) = _T185
    _T193 = 0
    _T192 = (_T192 + _T191)
_L47:
    _T191 = (_T191 - _T189)
    if (_T191 == 0) branch _L48
    _T192 = (_T192 - _T189)
    *(_T192 + 0) = _T193
    branch _L47
_L48:
    _T194 = *(_T10 + 4)
    _T195 = *(_T10 + 8)
    _T196 = *(_T19 + 4)
    _T197 = *(_T19 + 8)
    _T198 = (_T194 + _T196)
    _T199 = (_T195 + _T197)
    *(_T192 + 4) = _T198
    *(_T192 + 8) = _T199
    _T200 = *(_T192 + 4)
    _T201 = *(_T192 + 8)
    *(_T28 + 4) = _T200
    *(_T28 + 8) = _T201
    _T202 = 3
    _T203 = 0
    _T204 = (_T202 < _T203)
    if (_T204 == 0) branch _L49
    _T205 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T205
    call _PrintString
    call _Halt
_L49:
    _T206 = 4
    _T207 = (_T206 * _T202)
    _T208 = (_T206 + _T207)
    parm _T208
    _T209 =  call _Alloc
    *(_T209 + 0) = _T202
    _T210 = 0
    _T209 = (_T209 + _T208)
_L50:
    _T208 = (_T208 - _T206)
    if (_T208 == 0) branch _L51
    _T209 = (_T209 - _T206)
    *(_T209 + 0) = _T210
    branch _L50
_L51:
    _T211 = 3
    _T212 = 0
    _T213 = (_T211 < _T212)
    if (_T213 == 0) branch _L52
    _T214 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T214
    call _PrintString
    call _Halt
_L52:
    _T215 = 4
    _T216 = (_T215 * _T211)
    _T217 = (_T215 + _T216)
    parm _T217
    _T218 =  call _Alloc
    *(_T218 + 0) = _T211
    _T219 = 0
    _T218 = (_T218 + _T217)
_L53:
    _T217 = (_T217 - _T215)
    if (_T217 == 0) branch _L54
    _T218 = (_T218 - _T215)
    *(_T218 + 0) = _T219
    branch _L53
_L54:
    *(_T218 + 4) = _T31
    _T220 = 0
    *(_T218 + 8) = _T220
    _T221 = *(_T10 + 4)
    _T222 = *(_T10 + 8)
    _T223 = *(_T218 + 4)
    _T224 = *(_T218 + 8)
    _T225 = (_T221 + _T223)
    _T226 = (_T222 + _T224)
    *(_T209 + 4) = _T225
    *(_T209 + 8) = _T226
    _T227 = *(_T209 + 4)
    _T228 = *(_T209 + 8)
    *(_T28 + 4) = _T227
    *(_T28 + 8) = _T228
    _T229 = 3
    _T230 = 0
    _T231 = (_T229 < _T230)
    if (_T231 == 0) branch _L55
    _T232 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T232
    call _PrintString
    call _Halt
_L55:
    _T233 = 4
    _T234 = (_T233 * _T229)
    _T235 = (_T233 + _T234)
    parm _T235
    _T236 =  call _Alloc
    *(_T236 + 0) = _T229
    _T237 = 0
    _T236 = (_T236 + _T235)
_L56:
    _T235 = (_T235 - _T233)
    if (_T235 == 0) branch _L57
    _T236 = (_T236 - _T233)
    *(_T236 + 0) = _T237
    branch _L56
_L57:
    _T238 = 0
    *(_T236 + 4) = _T238
    _T239 = 0
    *(_T236 + 8) = _T239
    _T240 = 3
    _T241 = 0
    _T242 = (_T240 < _T241)
    if (_T242 == 0) branch _L58
    _T243 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T243
    call _PrintString
    call _Halt
_L58:
    _T244 = 4
    _T245 = (_T244 * _T240)
    _T246 = (_T244 + _T245)
    parm _T246
    _T247 =  call _Alloc
    *(_T247 + 0) = _T240
    _T248 = 0
    _T247 = (_T247 + _T246)
_L59:
    _T246 = (_T246 - _T244)
    if (_T246 == 0) branch _L60
    _T247 = (_T247 - _T244)
    *(_T247 + 0) = _T248
    branch _L59
_L60:
    _T249 = *(_T10 + 4)
    _T250 = *(_T10 + 8)
    _T251 = *(_T236 + 4)
    _T252 = *(_T236 + 8)
    _T253 = (_T249 + _T251)
    _T254 = (_T250 + _T252)
    *(_T247 + 4) = _T253
    *(_T247 + 8) = _T254
    _T255 = *(_T247 + 4)
    _T256 = *(_T247 + 8)
    *(_T28 + 4) = _T255
    *(_T28 + 8) = _T256
    _T257 = 3
    _T258 = 0
    _T259 = (_T257 < _T258)
    if (_T259 == 0) branch _L61
    _T260 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T260
    call _PrintString
    call _Halt
_L61:
    _T261 = 4
    _T262 = (_T261 * _T257)
    _T263 = (_T261 + _T262)
    parm _T263
    _T264 =  call _Alloc
    *(_T264 + 0) = _T257
    _T265 = 0
    _T264 = (_T264 + _T263)
_L62:
    _T263 = (_T263 - _T261)
    if (_T263 == 0) branch _L63
    _T264 = (_T264 - _T261)
    *(_T264 + 0) = _T265
    branch _L62
_L63:
    _T266 = 0
    *(_T264 + 4) = _T266
    _T267 = 0
    *(_T264 + 8) = _T267
    _T268 = 3
    _T269 = 0
    _T270 = (_T268 < _T269)
    if (_T270 == 0) branch _L64
    _T271 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T271
    call _PrintString
    call _Halt
_L64:
    _T272 = 4
    _T273 = (_T272 * _T268)
    _T274 = (_T272 + _T273)
    parm _T274
    _T275 =  call _Alloc
    *(_T275 + 0) = _T268
    _T276 = 0
    _T275 = (_T275 + _T274)
_L65:
    _T274 = (_T274 - _T272)
    if (_T274 == 0) branch _L66
    _T275 = (_T275 - _T272)
    *(_T275 + 0) = _T276
    branch _L65
_L66:
    _T277 = 3
    _T278 = 0
    _T279 = (_T277 < _T278)
    if (_T279 == 0) branch _L67
    _T280 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T280
    call _PrintString
    call _Halt
_L67:
    _T281 = 4
    _T282 = (_T281 * _T277)
    _T283 = (_T281 + _T282)
    parm _T283
    _T284 =  call _Alloc
    *(_T284 + 0) = _T277
    _T285 = 0
    _T284 = (_T284 + _T283)
_L68:
    _T283 = (_T283 - _T281)
    if (_T283 == 0) branch _L69
    _T284 = (_T284 - _T281)
    *(_T284 + 0) = _T285
    branch _L68
_L69:
    *(_T284 + 4) = _T31
    _T286 = 0
    *(_T284 + 8) = _T286
    _T287 = *(_T264 + 4)
    _T288 = *(_T264 + 8)
    _T289 = *(_T284 + 4)
    _T290 = *(_T284 + 8)
    _T291 = (_T287 + _T289)
    _T292 = (_T288 + _T290)
    *(_T275 + 4) = _T291
    *(_T275 + 8) = _T292
    _T293 = *(_T275 + 4)
    _T294 = *(_T275 + 8)
    *(_T28 + 4) = _T293
    *(_T28 + 8) = _T294
    _T295 = 4
    _T296 = (_T295 + _T31)
    _T34 = _T296
    parm _T34
    call _PrintInt
    _T297 = "\n"
    parm _T297
    call _PrintString
    _T298 = *(_T28 + 4)
    parm _T298
    call _PrintInt
    _T299 = "+"
    parm _T299
    call _PrintString
    _T300 = *(_T28 + 8)
    parm _T300
    call _PrintInt
    _T301 = "j"
    parm _T301
    call _PrintString
    _T302 = "\n"
    parm _T302
    call _PrintString
    _T303 = 3
    _T304 = 0
    _T305 = (_T303 < _T304)
    if (_T305 == 0) branch _L70
    _T306 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T306
    call _PrintString
    call _Halt
_L70:
    _T307 = 4
    _T308 = (_T307 * _T303)
    _T309 = (_T307 + _T308)
    parm _T309
    _T310 =  call _Alloc
    *(_T310 + 0) = _T303
    _T311 = 0
    _T310 = (_T310 + _T309)
_L71:
    _T309 = (_T309 - _T307)
    if (_T309 == 0) branch _L72
    _T310 = (_T310 - _T307)
    *(_T310 + 0) = _T311
    branch _L71
_L72:
    _T312 = *(_T10 + 4)
    _T313 = *(_T10 + 8)
    _T314 = *(_T19 + 4)
    _T315 = *(_T19 + 8)
    _T316 = (_T312 * _T314)
    _T317 = (_T313 * _T315)
    _T318 = (_T316 - _T317)
    _T319 = (_T312 * _T315)
    _T320 = (_T313 * _T314)
    _T321 = (_T319 + _T320)
    *(_T310 + 4) = _T318
    *(_T310 + 8) = _T321
    _T322 = *(_T310 + 4)
    _T323 = *(_T310 + 8)
    *(_T28 + 4) = _T322
    *(_T28 + 8) = _T323
    _T324 = 3
    _T325 = 0
    _T326 = (_T324 < _T325)
    if (_T326 == 0) branch _L73
    _T327 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T327
    call _PrintString
    call _Halt
_L73:
    _T328 = 4
    _T329 = (_T328 * _T324)
    _T330 = (_T328 + _T329)
    parm _T330
    _T331 =  call _Alloc
    *(_T331 + 0) = _T324
    _T332 = 0
    _T331 = (_T331 + _T330)
_L74:
    _T330 = (_T330 - _T328)
    if (_T330 == 0) branch _L75
    _T331 = (_T331 - _T328)
    *(_T331 + 0) = _T332
    branch _L74
_L75:
    _T333 = 3
    _T334 = 0
    _T335 = (_T333 < _T334)
    if (_T335 == 0) branch _L76
    _T336 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T336
    call _PrintString
    call _Halt
_L76:
    _T337 = 4
    _T338 = (_T337 * _T333)
    _T339 = (_T337 + _T338)
    parm _T339
    _T340 =  call _Alloc
    *(_T340 + 0) = _T333
    _T341 = 0
    _T340 = (_T340 + _T339)
_L77:
    _T339 = (_T339 - _T337)
    if (_T339 == 0) branch _L78
    _T340 = (_T340 - _T337)
    *(_T340 + 0) = _T341
    branch _L77
_L78:
    *(_T340 + 4) = _T31
    _T342 = 0
    *(_T340 + 8) = _T342
    _T343 = *(_T10 + 4)
    _T344 = *(_T10 + 8)
    _T345 = *(_T340 + 4)
    _T346 = *(_T340 + 8)
    _T347 = (_T343 * _T345)
    _T348 = (_T344 * _T346)
    _T349 = (_T347 - _T348)
    _T350 = (_T343 * _T346)
    _T351 = (_T344 * _T345)
    _T352 = (_T350 + _T351)
    *(_T331 + 4) = _T349
    *(_T331 + 8) = _T352
    _T353 = *(_T331 + 4)
    _T354 = *(_T331 + 8)
    *(_T28 + 4) = _T353
    *(_T28 + 8) = _T354
    _T355 = 3
    _T356 = 0
    _T357 = (_T355 < _T356)
    if (_T357 == 0) branch _L79
    _T358 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T358
    call _PrintString
    call _Halt
_L79:
    _T359 = 4
    _T360 = (_T359 * _T355)
    _T361 = (_T359 + _T360)
    parm _T361
    _T362 =  call _Alloc
    *(_T362 + 0) = _T355
    _T363 = 0
    _T362 = (_T362 + _T361)
_L80:
    _T361 = (_T361 - _T359)
    if (_T361 == 0) branch _L81
    _T362 = (_T362 - _T359)
    *(_T362 + 0) = _T363
    branch _L80
_L81:
    _T364 = 0
    *(_T362 + 4) = _T364
    _T365 = 0
    *(_T362 + 8) = _T365
    _T366 = 3
    _T367 = 0
    _T368 = (_T366 < _T367)
    if (_T368 == 0) branch _L82
    _T369 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T369
    call _PrintString
    call _Halt
_L82:
    _T370 = 4
    _T371 = (_T370 * _T366)
    _T372 = (_T370 + _T371)
    parm _T372
    _T373 =  call _Alloc
    *(_T373 + 0) = _T366
    _T374 = 0
    _T373 = (_T373 + _T372)
_L83:
    _T372 = (_T372 - _T370)
    if (_T372 == 0) branch _L84
    _T373 = (_T373 - _T370)
    *(_T373 + 0) = _T374
    branch _L83
_L84:
    _T375 = *(_T10 + 4)
    _T376 = *(_T10 + 8)
    _T377 = *(_T362 + 4)
    _T378 = *(_T362 + 8)
    _T379 = (_T375 * _T377)
    _T380 = (_T376 * _T378)
    _T381 = (_T379 - _T380)
    _T382 = (_T375 * _T378)
    _T383 = (_T376 * _T377)
    _T384 = (_T382 + _T383)
    *(_T373 + 4) = _T381
    *(_T373 + 8) = _T384
    _T385 = *(_T373 + 4)
    _T386 = *(_T373 + 8)
    *(_T28 + 4) = _T385
    *(_T28 + 8) = _T386
    _T387 = 3
    _T388 = 0
    _T389 = (_T387 < _T388)
    if (_T389 == 0) branch _L85
    _T390 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T390
    call _PrintString
    call _Halt
_L85:
    _T391 = 4
    _T392 = (_T391 * _T387)
    _T393 = (_T391 + _T392)
    parm _T393
    _T394 =  call _Alloc
    *(_T394 + 0) = _T387
    _T395 = 0
    _T394 = (_T394 + _T393)
_L86:
    _T393 = (_T393 - _T391)
    if (_T393 == 0) branch _L87
    _T394 = (_T394 - _T391)
    *(_T394 + 0) = _T395
    branch _L86
_L87:
    _T396 = 0
    *(_T394 + 4) = _T396
    _T397 = 0
    *(_T394 + 8) = _T397
    _T398 = 3
    _T399 = 0
    _T400 = (_T398 < _T399)
    if (_T400 == 0) branch _L88
    _T401 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T401
    call _PrintString
    call _Halt
_L88:
    _T402 = 4
    _T403 = (_T402 * _T398)
    _T404 = (_T402 + _T403)
    parm _T404
    _T405 =  call _Alloc
    *(_T405 + 0) = _T398
    _T406 = 0
    _T405 = (_T405 + _T404)
_L89:
    _T404 = (_T404 - _T402)
    if (_T404 == 0) branch _L90
    _T405 = (_T405 - _T402)
    *(_T405 + 0) = _T406
    branch _L89
_L90:
    _T407 = 3
    _T408 = 0
    _T409 = (_T407 < _T408)
    if (_T409 == 0) branch _L91
    _T410 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T410
    call _PrintString
    call _Halt
_L91:
    _T411 = 4
    _T412 = (_T411 * _T407)
    _T413 = (_T411 + _T412)
    parm _T413
    _T414 =  call _Alloc
    *(_T414 + 0) = _T407
    _T415 = 0
    _T414 = (_T414 + _T413)
_L92:
    _T413 = (_T413 - _T411)
    if (_T413 == 0) branch _L93
    _T414 = (_T414 - _T411)
    *(_T414 + 0) = _T415
    branch _L92
_L93:
    *(_T414 + 4) = _T31
    _T416 = 0
    *(_T414 + 8) = _T416
    _T417 = *(_T394 + 4)
    _T418 = *(_T394 + 8)
    _T419 = *(_T414 + 4)
    _T420 = *(_T414 + 8)
    _T421 = (_T417 * _T419)
    _T422 = (_T418 * _T420)
    _T423 = (_T421 - _T422)
    _T424 = (_T417 * _T420)
    _T425 = (_T418 * _T419)
    _T426 = (_T424 + _T425)
    *(_T405 + 4) = _T423
    *(_T405 + 8) = _T426
    _T427 = *(_T405 + 4)
    _T428 = *(_T405 + 8)
    *(_T28 + 4) = _T427
    *(_T28 + 8) = _T428
    _T429 = 4
    _T430 = (_T429 * _T31)
    _T34 = _T430
    parm _T34
    call _PrintInt
    _T431 = "\n"
    parm _T431
    call _PrintString
    _T432 = *(_T28 + 4)
    parm _T432
    call _PrintInt
    _T433 = "+"
    parm _T433
    call _PrintString
    _T434 = *(_T28 + 8)
    parm _T434
    call _PrintInt
    _T435 = "j"
    parm _T435
    call _PrintString
    _T436 = "\n"
    parm _T436
    call _PrintString
}


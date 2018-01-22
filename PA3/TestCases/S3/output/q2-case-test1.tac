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
    _T4 = 2
    _T3 = _T4
    _T7 = 2
    _T5 = _T7
    _T8 = 3
    _T6 = _T8
    _T10 = 3
    _T11 = (_T3 * _T10)
    _T12 = 100
    _T9 = _T12
_L10:
    _T13 = 0
    _T14 = (_T11 == _T13)
    if (_T14 == 0) branch _L11
    _T15 = (_T5 + _T6)
    _T9 = _T15
_L11:
    _T16 = 3
    _T17 = (_T11 == _T16)
    if (_T17 == 0) branch _L12
    _T18 = 3
    _T19 = (_T5 + _T18)
    _T9 = _T19
_L12:
    _T20 = 6
    _T21 = (_T11 == _T20)
    if (_T21 == 0) branch _L13
    _T22 = 2
    _T23 = (_T6 * _T22)
    _T24 = 6
    _T25 = (_T23 + _T24)
    _T9 = _T25
_L13:
    _T5 = _T9
    parm _T5
    call _PrintInt
}


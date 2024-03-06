/****************************************************************************
** Meta object code from reading C++ file 'teacherremovedata.h'
**
** Created by: The Qt Meta Object Compiler version 68 (Qt 6.6.1)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../PROJEKT_JIPP_ZAL/teacherremovedata.h"
#include <QtCore/qmetatype.h>

#if __has_include(<QtCore/qtmochelpers.h>)
#include <QtCore/qtmochelpers.h>
#else
QT_BEGIN_MOC_NAMESPACE
#endif


#include <memory>

#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'teacherremovedata.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 68
#error "This file was generated using the moc from 6.6.1. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

#ifndef Q_CONSTINIT
#define Q_CONSTINIT
#endif

QT_WARNING_PUSH
QT_WARNING_DISABLE_DEPRECATED
QT_WARNING_DISABLE_GCC("-Wuseless-cast")
namespace {

#ifdef QT_MOC_HAS_STRINGDATA
struct qt_meta_stringdata_CLASSTeacherRemoveDataENDCLASS_t {};
static constexpr auto qt_meta_stringdata_CLASSTeacherRemoveDataENDCLASS = QtMocHelpers::stringData(
    "TeacherRemoveData",
    "on_cancelPushButton_clicked",
    "",
    "on_behaviorRadioButton_clicked",
    "on_gradeRadioButton_clicked",
    "on_deletePushButton_clicked",
    "on_subjectComboBox_currentIndexChanged",
    "on_personComboBox_currentIndexChanged"
);
#else  // !QT_MOC_HAS_STRING_DATA
struct qt_meta_stringdata_CLASSTeacherRemoveDataENDCLASS_t {
    uint offsetsAndSizes[16];
    char stringdata0[18];
    char stringdata1[28];
    char stringdata2[1];
    char stringdata3[31];
    char stringdata4[28];
    char stringdata5[28];
    char stringdata6[39];
    char stringdata7[38];
};
#define QT_MOC_LITERAL(ofs, len) \
    uint(sizeof(qt_meta_stringdata_CLASSTeacherRemoveDataENDCLASS_t::offsetsAndSizes) + ofs), len 
Q_CONSTINIT static const qt_meta_stringdata_CLASSTeacherRemoveDataENDCLASS_t qt_meta_stringdata_CLASSTeacherRemoveDataENDCLASS = {
    {
        QT_MOC_LITERAL(0, 17),  // "TeacherRemoveData"
        QT_MOC_LITERAL(18, 27),  // "on_cancelPushButton_clicked"
        QT_MOC_LITERAL(46, 0),  // ""
        QT_MOC_LITERAL(47, 30),  // "on_behaviorRadioButton_clicked"
        QT_MOC_LITERAL(78, 27),  // "on_gradeRadioButton_clicked"
        QT_MOC_LITERAL(106, 27),  // "on_deletePushButton_clicked"
        QT_MOC_LITERAL(134, 38),  // "on_subjectComboBox_currentInd..."
        QT_MOC_LITERAL(173, 37)   // "on_personComboBox_currentInde..."
    },
    "TeacherRemoveData",
    "on_cancelPushButton_clicked",
    "",
    "on_behaviorRadioButton_clicked",
    "on_gradeRadioButton_clicked",
    "on_deletePushButton_clicked",
    "on_subjectComboBox_currentIndexChanged",
    "on_personComboBox_currentIndexChanged"
};
#undef QT_MOC_LITERAL
#endif // !QT_MOC_HAS_STRING_DATA
} // unnamed namespace

Q_CONSTINIT static const uint qt_meta_data_CLASSTeacherRemoveDataENDCLASS[] = {

 // content:
      12,       // revision
       0,       // classname
       0,    0, // classinfo
       6,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

 // slots: name, argc, parameters, tag, flags, initial metatype offsets
       1,    0,   50,    2, 0x08,    1 /* Private */,
       3,    0,   51,    2, 0x08,    2 /* Private */,
       4,    0,   52,    2, 0x08,    3 /* Private */,
       5,    0,   53,    2, 0x08,    4 /* Private */,
       6,    0,   54,    2, 0x08,    5 /* Private */,
       7,    0,   55,    2, 0x08,    6 /* Private */,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,

       0        // eod
};

Q_CONSTINIT const QMetaObject TeacherRemoveData::staticMetaObject = { {
    QMetaObject::SuperData::link<QDialog::staticMetaObject>(),
    qt_meta_stringdata_CLASSTeacherRemoveDataENDCLASS.offsetsAndSizes,
    qt_meta_data_CLASSTeacherRemoveDataENDCLASS,
    qt_static_metacall,
    nullptr,
    qt_incomplete_metaTypeArray<qt_meta_stringdata_CLASSTeacherRemoveDataENDCLASS_t,
        // Q_OBJECT / Q_GADGET
        QtPrivate::TypeAndForceComplete<TeacherRemoveData, std::true_type>,
        // method 'on_cancelPushButton_clicked'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'on_behaviorRadioButton_clicked'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'on_gradeRadioButton_clicked'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'on_deletePushButton_clicked'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'on_subjectComboBox_currentIndexChanged'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'on_personComboBox_currentIndexChanged'
        QtPrivate::TypeAndForceComplete<void, std::false_type>
    >,
    nullptr
} };

void TeacherRemoveData::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        auto *_t = static_cast<TeacherRemoveData *>(_o);
        (void)_t;
        switch (_id) {
        case 0: _t->on_cancelPushButton_clicked(); break;
        case 1: _t->on_behaviorRadioButton_clicked(); break;
        case 2: _t->on_gradeRadioButton_clicked(); break;
        case 3: _t->on_deletePushButton_clicked(); break;
        case 4: _t->on_subjectComboBox_currentIndexChanged(); break;
        case 5: _t->on_personComboBox_currentIndexChanged(); break;
        default: ;
        }
    }
    (void)_a;
}

const QMetaObject *TeacherRemoveData::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *TeacherRemoveData::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_CLASSTeacherRemoveDataENDCLASS.stringdata0))
        return static_cast<void*>(this);
    return QDialog::qt_metacast(_clname);
}

int TeacherRemoveData::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QDialog::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 6)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 6;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 6)
            *reinterpret_cast<QMetaType *>(_a[0]) = QMetaType();
        _id -= 6;
    }
    return _id;
}
QT_WARNING_POP

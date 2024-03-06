/********************************************************************************
** Form generated from reading UI file 'teacherremovedata.ui'
**
** Created by: Qt User Interface Compiler version 6.6.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_TEACHERREMOVEDATA_H
#define UI_TEACHERREMOVEDATA_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QComboBox>
#include <QtWidgets/QDialog>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QRadioButton>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QVBoxLayout>

QT_BEGIN_NAMESPACE

class Ui_TeacherRemoveData
{
public:
    QVBoxLayout *verticalLayout_5;
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout_2;
    QRadioButton *behaviorRadioButton;
    QRadioButton *gradeRadioButton;
    QHBoxLayout *horizontalLayout;
    QVBoxLayout *verticalLayout_2;
    QLabel *personLabel;
    QComboBox *personComboBox;
    QVBoxLayout *verticalLayout_4;
    QLabel *deleteLabel;
    QComboBox *deleteComboBox;
    QVBoxLayout *verticalLayout_3;
    QLabel *subjectLabel;
    QComboBox *subjectComboBox;
    QSpacerItem *verticalSpacer;
    QHBoxLayout *horizontalLayout_3;
    QSpacerItem *horizontalSpacer;
    QPushButton *cancelPushButton;
    QPushButton *deletePushButton;

    void setupUi(QDialog *TeacherRemoveData)
    {
        if (TeacherRemoveData->objectName().isEmpty())
            TeacherRemoveData->setObjectName("TeacherRemoveData");
        TeacherRemoveData->resize(634, 272);
        TeacherRemoveData->setStyleSheet(QString::fromUtf8("background-color: rgb(90, 90, 90);"));
        verticalLayout_5 = new QVBoxLayout(TeacherRemoveData);
        verticalLayout_5->setObjectName("verticalLayout_5");
        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName("verticalLayout");
        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        behaviorRadioButton = new QRadioButton(TeacherRemoveData);
        behaviorRadioButton->setObjectName("behaviorRadioButton");
        behaviorRadioButton->setStyleSheet(QString::fromUtf8("font: 700 16pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(behaviorRadioButton);

        gradeRadioButton = new QRadioButton(TeacherRemoveData);
        gradeRadioButton->setObjectName("gradeRadioButton");
        gradeRadioButton->setStyleSheet(QString::fromUtf8("font: 700 16pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(gradeRadioButton);


        verticalLayout->addLayout(horizontalLayout_2);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setObjectName("verticalLayout_2");
        personLabel = new QLabel(TeacherRemoveData);
        personLabel->setObjectName("personLabel");
        personLabel->setStyleSheet(QString::fromUtf8("font: 700 16pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout_2->addWidget(personLabel);

        personComboBox = new QComboBox(TeacherRemoveData);
        personComboBox->setObjectName("personComboBox");
        personComboBox->setStyleSheet(QString::fromUtf8("font: 700 16pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout_2->addWidget(personComboBox);


        horizontalLayout->addLayout(verticalLayout_2);

        verticalLayout_4 = new QVBoxLayout();
        verticalLayout_4->setObjectName("verticalLayout_4");
        deleteLabel = new QLabel(TeacherRemoveData);
        deleteLabel->setObjectName("deleteLabel");
        deleteLabel->setStyleSheet(QString::fromUtf8("font: 700 16pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout_4->addWidget(deleteLabel);

        deleteComboBox = new QComboBox(TeacherRemoveData);
        deleteComboBox->setObjectName("deleteComboBox");
        deleteComboBox->setStyleSheet(QString::fromUtf8("font: 700 16pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout_4->addWidget(deleteComboBox);


        horizontalLayout->addLayout(verticalLayout_4);


        verticalLayout->addLayout(horizontalLayout);

        verticalLayout_3 = new QVBoxLayout();
        verticalLayout_3->setObjectName("verticalLayout_3");
        subjectLabel = new QLabel(TeacherRemoveData);
        subjectLabel->setObjectName("subjectLabel");
        subjectLabel->setStyleSheet(QString::fromUtf8("font: 700 16pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout_3->addWidget(subjectLabel);

        subjectComboBox = new QComboBox(TeacherRemoveData);
        subjectComboBox->setObjectName("subjectComboBox");
        subjectComboBox->setStyleSheet(QString::fromUtf8("font: 700 16pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout_3->addWidget(subjectComboBox);


        verticalLayout->addLayout(verticalLayout_3);

        verticalSpacer = new QSpacerItem(20, 40, QSizePolicy::Minimum, QSizePolicy::Expanding);

        verticalLayout->addItem(verticalSpacer);

        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setObjectName("horizontalLayout_3");
        horizontalSpacer = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout_3->addItem(horizontalSpacer);

        cancelPushButton = new QPushButton(TeacherRemoveData);
        cancelPushButton->setObjectName("cancelPushButton");
        cancelPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        cancelPushButton->setStyleSheet(QString::fromUtf8("font: 700 16pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout_3->addWidget(cancelPushButton);

        deletePushButton = new QPushButton(TeacherRemoveData);
        deletePushButton->setObjectName("deletePushButton");
        deletePushButton->setCursor(QCursor(Qt::OpenHandCursor));
        deletePushButton->setStyleSheet(QString::fromUtf8("font: 700 16pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout_3->addWidget(deletePushButton);

        horizontalLayout_3->setStretch(0, 9);
        horizontalLayout_3->setStretch(1, 10);
        horizontalLayout_3->setStretch(2, 10);

        verticalLayout->addLayout(horizontalLayout_3);


        verticalLayout_5->addLayout(verticalLayout);


        retranslateUi(TeacherRemoveData);

        QMetaObject::connectSlotsByName(TeacherRemoveData);
    } // setupUi

    void retranslateUi(QDialog *TeacherRemoveData)
    {
        TeacherRemoveData->setWindowTitle(QCoreApplication::translate("TeacherRemoveData", "Dialog", nullptr));
        behaviorRadioButton->setText(QCoreApplication::translate("TeacherRemoveData", "Usu\305\204 Zachowanie", nullptr));
        gradeRadioButton->setText(QCoreApplication::translate("TeacherRemoveData", "Usu\305\204 Ocen\304\231", nullptr));
        personLabel->setText(QCoreApplication::translate("TeacherRemoveData", "Wybierz osobe:", nullptr));
        deleteLabel->setText(QCoreApplication::translate("TeacherRemoveData", "Wybierz co chces usun\304\205\304\207:", nullptr));
        subjectLabel->setText(QCoreApplication::translate("TeacherRemoveData", "Wybierz przedmiot: ", nullptr));
        cancelPushButton->setText(QCoreApplication::translate("TeacherRemoveData", "Anuluj", nullptr));
        deletePushButton->setText(QCoreApplication::translate("TeacherRemoveData", "Usu\305\204", nullptr));
    } // retranslateUi

};

namespace Ui {
    class TeacherRemoveData: public Ui_TeacherRemoveData {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_TEACHERREMOVEDATA_H

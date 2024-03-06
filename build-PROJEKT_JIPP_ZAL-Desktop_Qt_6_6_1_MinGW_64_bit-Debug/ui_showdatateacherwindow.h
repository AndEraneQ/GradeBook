/********************************************************************************
** Form generated from reading UI file 'showdatateacherwindow.ui'
**
** Created by: Qt User Interface Compiler version 6.6.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SHOWDATATEACHERWINDOW_H
#define UI_SHOWDATATEACHERWINDOW_H

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

class Ui_ShowDataTeacherWindow
{
public:
    QVBoxLayout *verticalLayout_3;
    QHBoxLayout *horizontalLayout;
    QVBoxLayout *verticalLayout;
    QRadioButton *gradesRadioButton;
    QRadioButton *behaviorRadioButton;
    QVBoxLayout *verticalLayout_2;
    QLabel *personLabel;
    QComboBox *personComboBox;
    QHBoxLayout *horizontalLayout_2;
    QSpacerItem *horizontalSpacer;
    QPushButton *cancelPushButton;
    QPushButton *nextPushButton;

    void setupUi(QDialog *ShowDataTeacherWindow)
    {
        if (ShowDataTeacherWindow->objectName().isEmpty())
            ShowDataTeacherWindow->setObjectName("ShowDataTeacherWindow");
        ShowDataTeacherWindow->resize(449, 203);
        ShowDataTeacherWindow->setStyleSheet(QString::fromUtf8("background-color: rgb(90, 90, 90);"));
        verticalLayout_3 = new QVBoxLayout(ShowDataTeacherWindow);
        verticalLayout_3->setObjectName("verticalLayout_3");
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName("verticalLayout");
        gradesRadioButton = new QRadioButton(ShowDataTeacherWindow);
        gradesRadioButton->setObjectName("gradesRadioButton");
        gradesRadioButton->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(gradesRadioButton);

        behaviorRadioButton = new QRadioButton(ShowDataTeacherWindow);
        behaviorRadioButton->setObjectName("behaviorRadioButton");
        behaviorRadioButton->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(behaviorRadioButton);


        horizontalLayout->addLayout(verticalLayout);

        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setObjectName("verticalLayout_2");
        personLabel = new QLabel(ShowDataTeacherWindow);
        personLabel->setObjectName("personLabel");
        personLabel->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout_2->addWidget(personLabel);

        personComboBox = new QComboBox(ShowDataTeacherWindow);
        personComboBox->setObjectName("personComboBox");
        personComboBox->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout_2->addWidget(personComboBox);


        horizontalLayout->addLayout(verticalLayout_2);


        verticalLayout_3->addLayout(horizontalLayout);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        horizontalSpacer = new QSpacerItem(68, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout_2->addItem(horizontalSpacer);

        cancelPushButton = new QPushButton(ShowDataTeacherWindow);
        cancelPushButton->setObjectName("cancelPushButton");
        cancelPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        cancelPushButton->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(cancelPushButton);

        nextPushButton = new QPushButton(ShowDataTeacherWindow);
        nextPushButton->setObjectName("nextPushButton");
        nextPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        nextPushButton->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(nextPushButton);


        verticalLayout_3->addLayout(horizontalLayout_2);


        retranslateUi(ShowDataTeacherWindow);

        QMetaObject::connectSlotsByName(ShowDataTeacherWindow);
    } // setupUi

    void retranslateUi(QDialog *ShowDataTeacherWindow)
    {
        ShowDataTeacherWindow->setWindowTitle(QCoreApplication::translate("ShowDataTeacherWindow", "Dialog", nullptr));
        gradesRadioButton->setText(QCoreApplication::translate("ShowDataTeacherWindow", "Poka\305\274 Oceny", nullptr));
        behaviorRadioButton->setText(QCoreApplication::translate("ShowDataTeacherWindow", "Poka\305\274 Zachowanie", nullptr));
        personLabel->setText(QCoreApplication::translate("ShowDataTeacherWindow", "Osoba:", nullptr));
        cancelPushButton->setText(QCoreApplication::translate("ShowDataTeacherWindow", "Anuluj", nullptr));
        nextPushButton->setText(QCoreApplication::translate("ShowDataTeacherWindow", "Dalej", nullptr));
    } // retranslateUi

};

namespace Ui {
    class ShowDataTeacherWindow: public Ui_ShowDataTeacherWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SHOWDATATEACHERWINDOW_H

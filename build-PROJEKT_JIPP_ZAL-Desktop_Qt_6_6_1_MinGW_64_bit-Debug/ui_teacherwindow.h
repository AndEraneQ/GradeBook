/********************************************************************************
** Form generated from reading UI file 'teacherwindow.ui'
**
** Created by: Qt User Interface Compiler version 6.6.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_TEACHERWINDOW_H
#define UI_TEACHERWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QVBoxLayout>

QT_BEGIN_NAMESPACE

class Ui_TeacherWindow
{
public:
    QVBoxLayout *verticalLayout_2;
    QHBoxLayout *horizontalLayout_2;
    QHBoxLayout *horizontalLayout;
    QLabel *personLabel;
    QLabel *nameLabel;
    QLabel *lastNameLabel;
    QSpacerItem *horizontalSpacer;
    QPushButton *showDataPushButton;
    QVBoxLayout *verticalLayout;
    QPushButton *gradePushButton;
    QPushButton *behaviorPushButton;
    QPushButton *showStudentDataPushButton;
    QPushButton *deleteDataPushButton;
    QPushButton *logoutPushButton;

    void setupUi(QDialog *TeacherWindow)
    {
        if (TeacherWindow->objectName().isEmpty())
            TeacherWindow->setObjectName("TeacherWindow");
        TeacherWindow->resize(455, 387);
        TeacherWindow->setStyleSheet(QString::fromUtf8("background-color: rgb(90, 90, 90);"));
        verticalLayout_2 = new QVBoxLayout(TeacherWindow);
        verticalLayout_2->setObjectName("verticalLayout_2");
        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        personLabel = new QLabel(TeacherWindow);
        personLabel->setObjectName("personLabel");
        personLabel->setStyleSheet(QString::fromUtf8("font: 700 14pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout->addWidget(personLabel);

        nameLabel = new QLabel(TeacherWindow);
        nameLabel->setObjectName("nameLabel");
        nameLabel->setStyleSheet(QString::fromUtf8("font: 700 14pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout->addWidget(nameLabel);

        lastNameLabel = new QLabel(TeacherWindow);
        lastNameLabel->setObjectName("lastNameLabel");
        lastNameLabel->setStyleSheet(QString::fromUtf8("font: 700 14pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout->addWidget(lastNameLabel);


        horizontalLayout_2->addLayout(horizontalLayout);

        horizontalSpacer = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout_2->addItem(horizontalSpacer);

        showDataPushButton = new QPushButton(TeacherWindow);
        showDataPushButton->setObjectName("showDataPushButton");
        showDataPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        showDataPushButton->setStyleSheet(QString::fromUtf8("font: 700 12pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(showDataPushButton);


        verticalLayout_2->addLayout(horizontalLayout_2);

        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName("verticalLayout");
        gradePushButton = new QPushButton(TeacherWindow);
        gradePushButton->setObjectName("gradePushButton");
        gradePushButton->setCursor(QCursor(Qt::OpenHandCursor));
        gradePushButton->setStyleSheet(QString::fromUtf8("font: 700 18pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(gradePushButton);

        behaviorPushButton = new QPushButton(TeacherWindow);
        behaviorPushButton->setObjectName("behaviorPushButton");
        behaviorPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        behaviorPushButton->setStyleSheet(QString::fromUtf8("font: 700 18pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(behaviorPushButton);

        showStudentDataPushButton = new QPushButton(TeacherWindow);
        showStudentDataPushButton->setObjectName("showStudentDataPushButton");
        showStudentDataPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        showStudentDataPushButton->setStyleSheet(QString::fromUtf8("font: 700 18pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(showStudentDataPushButton);

        deleteDataPushButton = new QPushButton(TeacherWindow);
        deleteDataPushButton->setObjectName("deleteDataPushButton");
        deleteDataPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        deleteDataPushButton->setStyleSheet(QString::fromUtf8("font: 700 18pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(deleteDataPushButton);

        logoutPushButton = new QPushButton(TeacherWindow);
        logoutPushButton->setObjectName("logoutPushButton");
        logoutPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        logoutPushButton->setStyleSheet(QString::fromUtf8("font: 700 18pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(logoutPushButton);


        verticalLayout_2->addLayout(verticalLayout);

        verticalLayout_2->setStretch(0, 1);
        verticalLayout_2->setStretch(1, 3);

        retranslateUi(TeacherWindow);

        QMetaObject::connectSlotsByName(TeacherWindow);
    } // setupUi

    void retranslateUi(QDialog *TeacherWindow)
    {
        TeacherWindow->setWindowTitle(QCoreApplication::translate("TeacherWindow", "Dialog", nullptr));
        personLabel->setText(QCoreApplication::translate("TeacherWindow", "Osoba: ", nullptr));
        nameLabel->setText(QString());
        lastNameLabel->setText(QString());
        showDataPushButton->setText(QCoreApplication::translate("TeacherWindow", "Pokaz moje dane", nullptr));
        gradePushButton->setText(QCoreApplication::translate("TeacherWindow", "Dodaj ocene", nullptr));
        behaviorPushButton->setText(QCoreApplication::translate("TeacherWindow", "Dodaj zachowanie", nullptr));
        showStudentDataPushButton->setText(QCoreApplication::translate("TeacherWindow", "Pokaz dane ucznia", nullptr));
        deleteDataPushButton->setText(QCoreApplication::translate("TeacherWindow", "Usu\305\204 dane ucznia", nullptr));
        logoutPushButton->setText(QCoreApplication::translate("TeacherWindow", "Wyloguj", nullptr));
    } // retranslateUi

};

namespace Ui {
    class TeacherWindow: public Ui_TeacherWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_TEACHERWINDOW_H

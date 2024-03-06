/********************************************************************************
** Form generated from reading UI file 'adminwindow.ui'
**
** Created by: Qt User Interface Compiler version 6.6.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_ADMINWINDOW_H
#define UI_ADMINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QVBoxLayout>

QT_BEGIN_NAMESPACE

class Ui_AdminWindow
{
public:
    QVBoxLayout *verticalLayout_2;
    QHBoxLayout *horizontalLayout;
    QSpacerItem *horizontalSpacer;
    QLabel *photoLabel;
    QSpacerItem *horizontalSpacer_2;
    QVBoxLayout *verticalLayout;
    QPushButton *addPushButton;
    QPushButton *deletePushButton;
    QPushButton *logoutPushButton;

    void setupUi(QDialog *AdminWindow)
    {
        if (AdminWindow->objectName().isEmpty())
            AdminWindow->setObjectName("AdminWindow");
        AdminWindow->resize(400, 300);
        AdminWindow->setStyleSheet(QString::fromUtf8("background-color: rgb(90, 90, 90);"));
        verticalLayout_2 = new QVBoxLayout(AdminWindow);
        verticalLayout_2->setObjectName("verticalLayout_2");
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        horizontalSpacer = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout->addItem(horizontalSpacer);

        photoLabel = new QLabel(AdminWindow);
        photoLabel->setObjectName("photoLabel");

        horizontalLayout->addWidget(photoLabel);

        horizontalSpacer_2 = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout->addItem(horizontalSpacer_2);


        verticalLayout_2->addLayout(horizontalLayout);

        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName("verticalLayout");
        addPushButton = new QPushButton(AdminWindow);
        addPushButton->setObjectName("addPushButton");
        addPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        addPushButton->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(addPushButton);

        deletePushButton = new QPushButton(AdminWindow);
        deletePushButton->setObjectName("deletePushButton");
        deletePushButton->setCursor(QCursor(Qt::OpenHandCursor));
        deletePushButton->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(deletePushButton);

        logoutPushButton = new QPushButton(AdminWindow);
        logoutPushButton->setObjectName("logoutPushButton");
        logoutPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        logoutPushButton->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(logoutPushButton);


        verticalLayout_2->addLayout(verticalLayout);


        retranslateUi(AdminWindow);

        QMetaObject::connectSlotsByName(AdminWindow);
    } // setupUi

    void retranslateUi(QDialog *AdminWindow)
    {
        AdminWindow->setWindowTitle(QCoreApplication::translate("AdminWindow", "Dialog", nullptr));
        photoLabel->setText(QString());
        addPushButton->setText(QCoreApplication::translate("AdminWindow", "Dodaj osob\304\231", nullptr));
        deletePushButton->setText(QCoreApplication::translate("AdminWindow", "Usu\305\204 osob\304\231", nullptr));
        logoutPushButton->setText(QCoreApplication::translate("AdminWindow", "Wyloguj", nullptr));
    } // retranslateUi

};

namespace Ui {
    class AdminWindow: public Ui_AdminWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_ADMINWINDOW_H

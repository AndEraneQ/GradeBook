/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 6.6.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralwidget;
    QHBoxLayout *horizontalLayout_2;
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout;
    QLabel *welcomeLabel;
    QLabel *photoLabel;
    QPushButton *loginPushButton;
    QPushButton *registerPushButton;
    QPushButton *cancelPushButton;
    QMenuBar *menubar;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName("MainWindow");
        MainWindow->resize(800, 500);
        MainWindow->setStyleSheet(QString::fromUtf8("background-color: rgb(90, 90, 90);"));
        centralwidget = new QWidget(MainWindow);
        centralwidget->setObjectName("centralwidget");
        horizontalLayout_2 = new QHBoxLayout(centralwidget);
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName("verticalLayout");
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        welcomeLabel = new QLabel(centralwidget);
        welcomeLabel->setObjectName("welcomeLabel");
        welcomeLabel->setCursor(QCursor(Qt::ArrowCursor));
        welcomeLabel->setLayoutDirection(Qt::LeftToRight);
        welcomeLabel->setStyleSheet(QString::fromUtf8("color: \"white\";\n"
"font: 700 italic 32pt \"Sitka\";\n"
"\n"
""));
        welcomeLabel->setTextFormat(Qt::RichText);
        welcomeLabel->setWordWrap(false);

        horizontalLayout->addWidget(welcomeLabel, 0, Qt::AlignHCenter);

        photoLabel = new QLabel(centralwidget);
        photoLabel->setObjectName("photoLabel");

        horizontalLayout->addWidget(photoLabel);

        horizontalLayout->setStretch(0, 4);
        horizontalLayout->setStretch(1, 2);

        verticalLayout->addLayout(horizontalLayout);

        loginPushButton = new QPushButton(centralwidget);
        loginPushButton->setObjectName("loginPushButton");
        loginPushButton->setCursor(QCursor(Qt::ClosedHandCursor));
        loginPushButton->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(loginPushButton);

        registerPushButton = new QPushButton(centralwidget);
        registerPushButton->setObjectName("registerPushButton");
        registerPushButton->setCursor(QCursor(Qt::ClosedHandCursor));
        registerPushButton->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";\n"
""));

        verticalLayout->addWidget(registerPushButton);

        cancelPushButton = new QPushButton(centralwidget);
        cancelPushButton->setObjectName("cancelPushButton");
        cancelPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        cancelPushButton->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";\n"
""));

        verticalLayout->addWidget(cancelPushButton);


        horizontalLayout_2->addLayout(verticalLayout);

        MainWindow->setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar->setObjectName("menubar");
        menubar->setGeometry(QRect(0, 0, 800, 21));
        MainWindow->setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar->setObjectName("statusbar");
        MainWindow->setStatusBar(statusbar);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QCoreApplication::translate("MainWindow", "MainWindow", nullptr));
        welcomeLabel->setText(QCoreApplication::translate("MainWindow", "Witam w e-dzienniku", nullptr));
        photoLabel->setText(QString());
        loginPushButton->setText(QCoreApplication::translate("MainWindow", "Zaloguj", nullptr));
        registerPushButton->setText(QCoreApplication::translate("MainWindow", "Zarejestruj", nullptr));
        cancelPushButton->setText(QCoreApplication::translate("MainWindow", "Wyj\305\233cie", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H

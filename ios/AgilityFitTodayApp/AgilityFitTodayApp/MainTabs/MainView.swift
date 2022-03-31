//
//  MainView.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 3/26/22.
//

import Foundation
import SwiftUI

struct MainView: View {
    var body: some View {
        TabView {
            HomeView()
                .tabItem {
                    Label("Home", systemImage: "house")
                }

            DashboardView()
                .tabItem {
                    Label("Dashboard", systemImage: "calendar")
                }
            
            NotificationsView()
                .tabItem {
                    Label("Notifcations", systemImage: "bell.circle.fill")
                }
        }
    }
}

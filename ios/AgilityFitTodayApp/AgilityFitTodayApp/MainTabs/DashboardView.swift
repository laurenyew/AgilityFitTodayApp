//
//  DashboardView.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 3/26/22.
//

import Foundation
import SwiftUI

struct DashboardView: View {
    var body: some View {
        NavigationView {
            VStack {
                Text("Dashboard")
            }
            .navigationTitle("Dashboard")
            .navigationBarTitleDisplayMode(.inline)
        }
    }
}

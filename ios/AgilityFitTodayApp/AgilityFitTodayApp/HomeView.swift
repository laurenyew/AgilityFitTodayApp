//
//  HomeView.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 3/21/22.
//

import Foundation
import SwiftUI

struct HomeView: View {
    var body: some View {
        NavigationView {
            VStack {
               Text("Hello World")
            }
        }.toolbar {
            Text("Home")
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
